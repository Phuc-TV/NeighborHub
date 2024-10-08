package neighborHub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.Entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDtoResponse {
    private int bookingId;
    private RegistrationForm registration;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime pickupTime;
    private LocalDateTime dropoffTime;
    private float distance;
    private String status;
    private User user;
}
