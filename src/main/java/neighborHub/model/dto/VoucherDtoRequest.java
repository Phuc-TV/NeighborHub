package neighborHub.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDtoRequest {
    @NotEmpty(message = "Code should not be empty!")
    private String code;
    @NotEmpty(message = "Description should not be empty!")
    private String description;
    @NotEmpty(message = "Discount should not be empty!")
    private float discount;
    @NotEmpty(message = "ExpiryDate should not be empty!")
    private LocalDate expiryDate;
}
