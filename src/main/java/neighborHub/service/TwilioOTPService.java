package neighborHub.service;

import neighborHub.model.dto.OTPRequestDto;
import neighborHub.model.dto.OTPResponeDto;
import org.springframework.http.ResponseEntity;

public interface TwilioOTPService {
    public ResponseEntity<OTPResponeDto> sendOTP(OTPRequestDto otpRequestDto);

    ResponseEntity<String> validateOTP(OTPRequestDto otpRequestDto);
}
