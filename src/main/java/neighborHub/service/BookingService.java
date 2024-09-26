package neighborHub.service;

import neighborHub.model.dto.BookingDtoResponse;
import neighborHub.model.dto.TripCostDTO;
import neighborHub.model.dto.TripCostResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    List<BookingDtoResponse> viewListAllBookings();

    ResponseEntity<TripCostResponseDto> calculateFare(TripCostDTO tripCostDTO);
}
