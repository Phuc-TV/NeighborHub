package neighborHub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

//    @PostMapping("/sendOTP")
//    public ResponseEntity<OTPResponeDto> sendOTP(@RequestBody OTPRequestDto dto) {
//        return twilioOTPService.sendOTP(dto);
//    }
//
//    @PostMapping("/validateOTP")
//    public ResponseEntity<String> validateOTP(@RequestBody OTPRequestDto dto) {
//        return twilioOTPService.validateOTP(dto);
//    }

    // Endpoint to start a new verification
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("/start-verification")
    public ResponseEntity<?> startVerification(@RequestParam String toPhoneNumber) {
        var verification = twilioOTPService.startVerification(toPhoneNumber);
        return ResponseEntity.ok(verification);
    }

    // Endpoint to check verification
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("/check-verification")
    public ResponseEntity<?> checkVerification(@RequestParam String toPhoneNumber, @RequestParam String code) {
        var verificationCheck = twilioOTPService.checkVerification(toPhoneNumber, code);
        return ResponseEntity.ok(verificationCheck);
    }

    // Endpoint to fetch a specific verification
    @GetMapping("/fetch-verification/{sid}")
    public ResponseEntity<?> fetchVerification(@PathVariable String sid) {
        var verification = twilioOTPService.fetchVerification(sid);
        return ResponseEntity.ok(verification);
    }
}
