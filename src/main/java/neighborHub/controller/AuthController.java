package neighborHub.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import neighborHub.model.dto.LoginDto;
import neighborHub.model.resquestModel.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import neighborHub.service.AuthService;
import neighborHub.model.dto.SignupDto;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        AuthenticationResponse token = authService.userLogin(loginDto, request);
        return ResponseEntity.ok(token);
    }


    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDto signupDto){
        String response = authService.userSignup(signupDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(authService.refreshToken(request, response));
    }
}
