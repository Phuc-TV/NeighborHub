package neighborHub.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import neighborHub.model.dto.OTPRequestDto;
import neighborHub.model.dto.OTPResponeDto;
import neighborHub.model.dto.OtpStatus;
import neighborHub.service.FareInfoService;
import neighborHub.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPServiceImpl implements TwilioOTPService {

    @Autowired
    private FareInfoService fareInfoService;

    Map<String, String> otpMap = new HashMap<>();

//    @Override
//    public ResponseEntity<OTPResponeDto> sendOTP(OTPRequestDto otpRequestDto) {
//        OTPResponeDto otpResponeDto;
//
//        try {
//            Twilio.init(fareInfoService.getFareInfo().getTwilio_Account_sid(),
//                    fareInfoService.getFareInfo().getTwilio_Auth_Token());
//            PhoneNumber to = new PhoneNumber(otpRequestDto.getPhoneNumber());
//            PhoneNumber from = new PhoneNumber(fareInfoService.getFareInfo().getTwilio_Trial_Number());
//            String otp = generateOTP();
//
//            System.out.println("Generated OTP: " + otp); // Ghi log OTP
//
//            String otpMessage="Mã xác thực NeighBor_Hub của bạn là: "+ otp;
//
//            Message message = Message.creator(to, from, otpMessage)
//                    .create();
//            otpMap.put(otpRequestDto.getPhoneNumber(), otp);
//            otpResponeDto = new OTPResponeDto(OtpStatus.DELIVERED, otpMessage);
//        } catch (Exception e) {
//            otpResponeDto = new OTPResponeDto(OtpStatus.FAILED, e.getMessage());
//        }
//        return new ResponseEntity<>(otpResponeDto, HttpStatus.OK);
//    }
//
//
//    @Override
//    public ResponseEntity<String> validateOTP(OTPRequestDto otpRequestDto) {
//        // Kiểm tra xem phoneNumber có tồn tại trong otpMap không
//        if (!otpMap.containsKey(otpRequestDto.getPhoneNumber())) {
//            return new ResponseEntity<>("No OTP found for the given phone number", HttpStatus.NOT_FOUND);
//        }
//
//        // So sánh userInputOTP với giá trị trong otpMap
//        if (otpRequestDto.getOTP().equals(otpMap.get(otpRequestDto.getPhoneNumber()))) {
//            return new ResponseEntity<>("Valid OTP", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid OTP, please retry", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    private String generateOTP()
//    {
//        return new DecimalFormat("000000")
//                .format(new Random().nextInt(999999));
//    }


    @Autowired
    public TwilioOTPServiceImpl(FareInfoService fareInfoService) {
        // Initialize Twilio SDK
        this.fareInfoService = fareInfoService;
    }


    @Override
    // Start a new verification
    public Verification startVerification(String toPhoneNumber, String channel) {
        Twilio.init(fareInfoService.getFareInfo().getTwilio_Account_sid(),
                fareInfoService.getFareInfo().getTwilio_Auth_Token());

        return Verification.creator(
                fareInfoService.getFareInfo().getTwilio_VerifyService_Sid(),
                toPhoneNumber,
                channel
        ).create();
    }

    @Override
    // Check a verification
    public VerificationCheck checkVerification(String toPhoneNumber, String code) {
        Twilio.init(fareInfoService.getFareInfo().getTwilio_Account_sid(),
                fareInfoService.getFareInfo().getTwilio_Auth_Token());

        return VerificationCheck.creator(
                        fareInfoService.getFareInfo().getTwilio_VerifyService_Sid()
                ).setTo(toPhoneNumber)
                .setCode(code)
                .create();
    }

    @Override
    // Fetch a specific verification status
    public Verification fetchVerification(String sid) {
        return Verification.fetcher(fareInfoService.getFareInfo().getTwilio_VerifyService_Sid(), sid).fetch();
    }
}
