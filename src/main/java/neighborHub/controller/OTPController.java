package neighborHub.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import neighborHub.model.dto.OTPRequestDto;
import neighborHub.resource.TwilioOTPHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/OTP")
public class OTPController {

    @Autowired
    private TwilioOTPHandler handler;

    @PostMapping("/sendOTP")
    public ResponseEntity<String> sendOTP(@RequestBody OTPRequestDto dto) {
        return handler.sendOTP(dto);
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<String> validateOTP(@RequestBody OTPRequestDto dto) {
        return handler.validateOTP(dto);
    }

    @GetMapping("/sendSMS/{toMobileNo}/{text}")
    public ResponseEntity sendSMS(@PathVariable("toMobileNo") String toMobileNo,@PathVariable("text") String text)
    {
        Twilio.init("AC9f2b6937529cb1fc1788577bfb76e315", "652a91e25d4c6fd76c2282d813f22ac3");

        Message.creator(new PhoneNumber(toMobileNo), new PhoneNumber("+18565954733") ,text).create();

        return new ResponseEntity("message sent successfully", HttpStatus.OK);
    }
}
