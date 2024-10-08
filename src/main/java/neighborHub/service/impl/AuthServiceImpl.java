package neighborHub.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import neighborHub.model.Entity.AccessToken;
import neighborHub.model.Entity.RefreshToken;
import neighborHub.model.Entity.User;
import neighborHub.model.dto.LoginDto;
import neighborHub.model.exception.ApiException;
import neighborHub.model.resquestModel.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import neighborHub.repository.AccessTokenRepository;
import neighborHub.repository.RefreshTokenRepository;
import neighborHub.repository.UserRepository;
import neighborHub.security.JwtTokenProvider;
import neighborHub.service.AuthService;
import neighborHub.model.dto.SignupDto;

import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AccessTokenRepository accessTokenRepository;
    private RefreshTokenRepository refreshTokenRepository;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapper modelMapper;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           AccessTokenRepository accessTokenRepository, RefreshTokenRepository refreshTokenRepository, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthenticationResponse userLogin(LoginDto loginDto, HttpServletRequest request) {
        // Kiểm tra số điện thoại/email tồn tại trước khi xác thực
        User user = userRepository.findByPhoneOrEmail(loginDto.getPhoneOrEmail(), loginDto.getPhoneOrEmail())
                .orElseThrow(() -> {
                    request.setAttribute("errorMessage", "Wrong phone");
                    return new ApiException(HttpStatus.BAD_REQUEST, "Wrong phone");
                });

        try {
            // Nếu người dùng tồn tại, tiếp tục xác thực mật khẩu
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getPhoneOrEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            revokeRefreshToken(accessToken);
            RefreshToken savedRefreshToken = saveUserRefreshToken(refreshToken);

            revokeAllUserAccessTokens(user);
            saveUserAccessToken(user, accessToken, savedRefreshToken);

            return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (BadCredentialsException ex) {
            // Bắt lỗi mật khẩu sai
            request.setAttribute("errorMessage", "Wrong password");
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
    }




    private void revokeRefreshToken(String accessToken) {
        AccessToken token = accessTokenRepository.findByToken(accessToken);
        if (token != null) {
            RefreshToken refreshToken = token.getRefreshToken();
            refreshToken.setRevoked(true);
            refreshToken.setExpired(true);
            refreshTokenRepository.save(refreshToken);
        }
    }

    private void revokeAllUserAccessTokens(User user) {
        var validUserTokens = accessTokenRepository.findAllValidTokensByUser(user.getUserId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(accessToken -> {
            accessToken.setRevoked(true);
            accessToken.setExpired(true);
        });
        accessTokenRepository.saveAll(validUserTokens);
    }

    private void saveUserAccessToken(User user, String jwtToken, RefreshToken refreshToken) {
        var token = AccessToken.builder()
                .user(user)
                .token(jwtToken)
                .refreshToken(refreshToken)
                .revoked(false)
                .expired(false)
                .build();
        accessTokenRepository.save(token);
    }

    private RefreshToken saveUserRefreshToken(String jwtToken) {
        var token = RefreshToken.builder()
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .build();
        return refreshTokenRepository.save(token);
    }

    @Override
    public String userSignup(SignupDto signupDto) {
        // Kiểm tra nếu username đã tồn tại
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exist!");
        }

        // Kiểm tra nếu phone đã tồn tại
        if (userRepository.existsByPhone(signupDto.getPhone())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Phone number is already exist!");
        }

        User user = modelMapper.map(signupDto, User.class);

        // Đặt giá trị mặc định cho email nếu null
        if (user.getEmail() == null) {
            user.setEmail(""); // Hoặc một giá trị mặc định khác
        }

        user.setStatus(true);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setRole("user");

        userRepository.save(user);

        return "User registered successfully!";
    }


    @Override
    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken).orElseThrow();
        username = jwtTokenProvider.getUsernameFromJwt(refreshToken);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenProvider.isTokenValid(refreshToken, userDetails.getUsername())
                    && !token.isRevoked() && !token.isExpired()
            ) {
                //map user to authentication
                Authentication userAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                var accessToken = jwtTokenProvider.generateAccessToken(userAuthentication);
                User user = this.userRepository.findByPhoneOrEmail(username, username).orElseThrow();
                revokeAllUserAccessTokens(user);
                saveUserAccessToken(user, accessToken, token);
                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }
        return null;
    }
}
