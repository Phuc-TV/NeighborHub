package neighborHub.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    @NotEmpty(message = "Phone number should not be empty!")
    @Pattern(regexp="(^$|[0-9]{10,11})", message = "Phone number must be 10 or 11 digits!")
    private String phone;

    @NotEmpty(message = "Password should not be empty!")
    private String password;

    @NotEmpty(message = "Username should not be empty!")
    private String username;
}
