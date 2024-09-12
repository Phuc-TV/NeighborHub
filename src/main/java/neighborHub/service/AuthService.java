package neighborHub.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import neighborHub.model.dto.LoginDto;
import neighborHub.model.dto.SignupDto;
import neighborHub.model.resquestModel.AuthenticationResponse;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponse userLogin(LoginDto loginDto);
    String userSignup(SignupDto signupDto);
    AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
