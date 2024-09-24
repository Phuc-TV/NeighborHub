package neighborHub.resource;

import neighborHub.model.dto.OTPRequestDto;
import neighborHub.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Component
public class TwilioOTPHandler {
    @Autowired
    private TwilioOTPService service;

    public ResponseEntity<String> sendOTP(OTPRequestDto dto) {
        try {
            service.sendOTPForPasswordReset(dto);
            return new ResponseEntity<>("OTP sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send OTP", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> validateOTP(OTPRequestDto dto) {
        return service.validateOTP(dto.getOTP(), dto.getPhoneNumber())
                .map(validResponse -> new ResponseEntity<>(validResponse, HttpStatus.OK))
                .onErrorResume(e -> {
                    String errorMessage = e instanceof IllegalArgumentException ? e.getMessage() : "An unexpected error occurred";
                    return Mono.just(new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST));
                })
                .block();
    }

}
