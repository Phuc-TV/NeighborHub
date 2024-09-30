package neighborHub.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverActiveRequestDto {
    private double userLat;
    private double userLon;
}
