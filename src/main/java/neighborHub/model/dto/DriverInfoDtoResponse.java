package neighborHub.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfoDtoResponse {
    private String username;

    private String phone;

    private String email;

    private int averageRating;

    private float revenue;
}
