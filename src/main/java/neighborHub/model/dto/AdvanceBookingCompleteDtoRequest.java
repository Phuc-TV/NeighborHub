package neighborHub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvanceBookingCompleteDtoRequest {
    private int advanceBookingId;
    private int registrationId;
}
