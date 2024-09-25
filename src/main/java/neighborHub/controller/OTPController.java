package neighborHub.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import neighborHub.model.dto.OTPRequestDto;
import neighborHub.model.dto.OTPResponeDto;
import neighborHub.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/OTP")
public class OTPController {

    @Autowired
    private TwilioOTPService twilioOTPService;

    @PostMapping("/sendOTP")
    public ResponseEntity<OTPResponeDto> sendOTP(@RequestBody OTPRequestDto dto) {
        return twilioOTPService.sendOTP(dto);
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<String> validateOTP(@RequestBody OTPRequestDto dto) {
        return twilioOTPService.validateOTP(dto);
    }
}
