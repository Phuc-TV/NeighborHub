package neighborHub.service;

import neighborHub.model.dto.BookingDtoResponse;
import neighborHub.model.dto.TripCostDTO;

import java.util.List;

public interface BookingService {
    List<BookingDtoResponse> viewListAllBookings();

    float calculateFare(TripCostDTO tripCostDTO);
}
