package neighborHub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvanceBookingDtoRequest {
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime pickupTime;
    private float distance;
    private Long userId;
}
