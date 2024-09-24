package neighborHub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.Entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDtoResponse {
    private int bookingId;
    private RegistrationForm registration;
    private String pickupLocation;
    private String dropoffLocation;
    private int pickupTime;
    private int dropoffTime;
    private int distance;
    private int status;
    private User user;
}
