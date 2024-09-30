package neighborHub.service;

import neighborHub.model.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    List<BookingDtoResponse> viewListAllBookings();

    ResponseEntity<List<TripCostResponseDto>> calculateFare(TripCostDTO tripCostDTO);

    ResponseEntity<List<DriverResponseDto>> getActiveDriver(DriverActiveRequestDto dto);
}
