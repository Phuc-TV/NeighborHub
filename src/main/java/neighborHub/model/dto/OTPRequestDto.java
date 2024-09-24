package neighborHub.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTPRequestDto {
    private String phoneNumber;
    private String OTP;
}
