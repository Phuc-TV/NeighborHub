package neighborHub.service.impl;

import com.twilio.type.PhoneNumber;
import neighborHub.config.TwilioConfig;
import neighborHub.model.dto.OTPRequestDto;
import neighborHub.model.dto.OTPResponeDto;
import neighborHub.model.dto.OtpStatus;
import neighborHub.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.twilio.rest.api.v2010.account.Message;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPServiceImpl implements TwilioOTPService {

    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> otpMap = new HashMap<>();

    @Override
    public Mono<OTPResponeDto> sendOTPForPasswordReset(OTPRequestDto otpRequestDto) {
        OTPResponeDto otpResponeDto;

        try {
            PhoneNumber to = new PhoneNumber(otpRequestDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();

            System.out.println("Generated OTP: " + otp); // Ghi log OTP

            String otpMessage="Mã xác thực NeighBor_Hub của bạn là: "+ otp;

            Message message = Message.creator(to, from, otpMessage)
                    .create();
            otpMap.put(otpRequestDto.getPhoneNumber(), otp);
            otpResponeDto = new OTPResponeDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            otpResponeDto = new OTPResponeDto(OtpStatus.FAILED, e.getMessage());
        }
        return Mono.just(otpResponeDto);
    }


    @Override
    public Mono<String> validateOTP(String userInputOTP, String phoneNumber) {
        // Kiểm tra xem phoneNumber có tồn tại trong otpMap không
        if (!otpMap.containsKey(phoneNumber)) {
            return Mono.error(new IllegalArgumentException("No OTP found for the given phone number"));
        }

        // So sánh userInputOTP với giá trị trong otpMap
        if (userInputOTP.equals(otpMap.get(phoneNumber))) {
            return Mono.just("Valid OTP, please proceed");
        } else {
            return Mono.error(new IllegalArgumentException("Invalid OTP, please retry"));
        }
    }

    private String generateOTP()
    {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
