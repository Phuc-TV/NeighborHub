//package neighborHub.service.impl;
//
//import com.twilio.Twilio;
//import neighborHub.service.FareInfoService;
//import neighborHub.service.TwilioOTPService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.twilio.rest.verify.v2.service.Verification;
//import com.twilio.rest.verify.v2.service.VerificationCheck;
//
//@Service
//public class TwilioOTPServiceImpl implements TwilioOTPService {
////
////    @Autowired
////    private FareInfoService fareInfoService;
//
////    Map<String, String> otpMap = new HashMap<>();
////
////    @Override
////    public ResponseEntity<OTPResponeDto> sendOTP(OTPRequestDto otpRequestDto) {
////        OTPResponeDto otpResponeDto;
////
////        try {
////            Twilio.init(fareInfoService.getFareInfo().getTwilio_Account_sid(),
////                    fareInfoService.getFareInfo().getTwilio_Auth_Token());
////            PhoneNumber to = new PhoneNumber(otpRequestDto.getPhoneNumber());
////            PhoneNumber from = new PhoneNumber(fareInfoService.getFareInfo().getTwilio_Trial_Number());
////            String otp = generateOTP();
////
////            System.out.println("Generated OTP: " + otp); // Ghi log OTP
////
////            String otpMessage="Mã xác thực NeighBor_Hub của bạn là: "+ otp;
////
////            Message message = Message.creator(to, from, otpMessage)
////                    .create();
////            otpMap.put(otpRequestDto.getPhoneNumber(), otp);
////            otpResponeDto = new OTPResponeDto(OtpStatus.DELIVERED, otpMessage);
////        } catch (Exception e) {
////            otpResponeDto = new OTPResponeDto(OtpStatus.FAILED, e.getMessage());
////        }
////        return new ResponseEntity<>(otpResponeDto, HttpStatus.OK);
////    }
////
////
////    @Override
////    public ResponseEntity<String> validateOTP(OTPRequestDto otpRequestDto) {
////        // Kiểm tra xem phoneNumber có tồn tại trong otpMap không
////        if (!otpMap.containsKey(otpRequestDto.getPhoneNumber())) {
////            return new ResponseEntity<>("No OTP found for the given phone number", HttpStatus.NOT_FOUND);
////        }
////
////        // So sánh userInputOTP với giá trị trong otpMap
////        if (otpRequestDto.getOTP().equals(otpMap.get(otpRequestDto.getPhoneNumber()))) {
////            return new ResponseEntity<>("Valid OTP", HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>("Invalid OTP, please retry", HttpStatus.BAD_REQUEST);
////        }
////    }
////
////    private String generateOTP()
////    {
////        return new DecimalFormat("000000")
////                .format(new Random().nextInt(999999));
////    }
//
////    String accountSid = fareInfoService.getFareInfo().getTwilio_Account_sid();
////    String authToken = fareInfoService.getFareInfo().getTwilio_Auth_Token();
////    String verifyServiceSid = "VAe29096c87deeb9adb209ae1ebb9a4a27";
////
////    public TwilioOTPServiceImpl() {
////        // Initialize Twilio SDK
////        Twilio.init("AC9f2b6937529cb1fc1788577bfb76e315", "df1b0a6c8942a3c06692ad4dda8c9eb1");
////    }
////
////    @Override
////    // Start a new verification
////    public Verification startVerification(String toPhoneNumber, String channel) {
////        return Verification.creator(
////                verifyServiceSid,
////                toPhoneNumber,
////                channel
////        ).create();
////    }
////
////    @Override
////    // Check a verification
////    public VerificationCheck checkVerification(String toPhoneNumber, String code) {
////        return VerificationCheck.creator(
////                        verifyServiceSid
////                ).setTo(toPhoneNumber)
////                .setCode(code)
////                .create();
////    }
////
////    @Override
////    // Fetch a specific verification status
////    public Verification fetchVerification(String sid) {
////        return Verification.fetcher(verifyServiceSid, sid).fetch();
//}
