package neighborHub.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty(message = "UserId should not be empty!")
    private Long userId;
    @NotEmpty(message = "Username should not be empty!")
    private String username;
    @NotEmpty(message = "Phone should not be empty!")
    private String phone;
    @NotEmpty(message = "Email should not be empty!")
    private String email;
}
