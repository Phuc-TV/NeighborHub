package neighborHub.model.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalPriceDtoRequest
{
    
    private List<Integer> listVoucher;
    private float tripCost;
}
