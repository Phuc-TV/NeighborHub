package neighborHub.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTPResponeDto {
    private OtpStatus status;
    private String message;
}
