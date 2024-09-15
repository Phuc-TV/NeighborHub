package neighborHub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDtoResponse {
    private int voucherId;
    private String code;
    private String description;
    private float discount;
    private LocalDate expiryDate;
}
