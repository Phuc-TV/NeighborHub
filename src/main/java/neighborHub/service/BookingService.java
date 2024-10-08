package neighborHub.service;

import neighborHub.model.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    List<BookingDtoResponse> viewListAllBookings();

    ResponseEntity<List<TripCostResponseDto>> calculateFare(TripCostDTO tripCostDTO);

    ResponseEntity<List<DriverResponseDto>> getActiveDriver(DriverActiveRequestDto dto);

    ResponseEntity<BookingDtoResponse> createBooking(BookingDtoRequest bookingDtoRequest);

    ResponseEntity<String> createAdvanceBooking(AdvanceBookingDtoRequest advanceBookingDtoRequest);

    ResponseEntity<List<AdvanceBookingDtoResponse>> listAdvanceBooking();

    ResponseEntity<String> createAdvanceBookingComplete(AdvanceBookingCompleteDtoRequest advanceBookingDtoRequest);

    ResponseEntity<List<BookingDtoResponse>> getBookingByUserId(Long userId);


    ResponseEntity<BookingDtoResponse> addDriver(AddDriverBookingDtoRequest driverBookingDtoRequest);

    ResponseEntity<Float> totalPrice(TotalPriceDtoRequest totalPriceDtoRequest);

    ResponseEntity<BookingDtoResponse> getBookingById(int bookingId);
}
