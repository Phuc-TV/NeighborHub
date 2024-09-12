package neighborHub.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Phone or email cannot be empty")
    private String phoneOrEmail;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
