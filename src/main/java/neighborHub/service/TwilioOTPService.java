package neighborHub.service;

import neighborHub.model.dto.OTPRequestDto;
import neighborHub.model.dto.OTPResponeDto;
import reactor.core.publisher.Mono;

public interface TwilioOTPService {
    public Mono<OTPResponeDto> sendOTPForPasswordReset(OTPRequestDto otpRequestDto);

    Mono<String> validateOTP(String userInputOTP, String phoneNumber);
}
