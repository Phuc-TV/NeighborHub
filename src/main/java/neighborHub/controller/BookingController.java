package neighborHub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import neighborHub.model.dto.*;
import neighborHub.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController (BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @GetMapping("/viewAllBooking")
    public ResponseEntity<List<BookingDtoResponse>> viewListAllBooking(){
        try {
            List<BookingDtoResponse> allBookingList = bookingService.viewListAllBookings();
            if(allBookingList != null){
                return new ResponseEntity<>(allBookingList, HttpStatus.OK);
            }else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("/calculateFare")
    public ResponseEntity<List<TripCostResponseDto>> calculateFare(@RequestBody TripCostDTO tripCostDTO) {
        return bookingService.calculateFare(tripCostDTO);
    }

    @GetMapping("/getDriverNearUser")
    public ResponseEntity<List<DriverResponseDto>> getDriverNearUser(@RequestBody DriverActiveRequestDto dto)
    {
        return bookingService.getActiveDriver(dto);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("createBooking")
    public ResponseEntity<String> createBooking(@RequestBody BookingDtoRequest bookingDtoRequest)
    {
        return bookingService.createBooking(bookingDtoRequest);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping("createAdvanceBooking")
    public ResponseEntity<String> createAdvanceBooking(@RequestBody AdvanceBookingDtoRequest advanceBookingDtoRequest)
    {
        return bookingService.createAdvanceBooking(advanceBookingDtoRequest);
    }

    @GetMapping("listAdvanceBooking")
    public ResponseEntity<List<AdvanceBookingDtoResponse>> listAdvanceBooking()
    {
        return bookingService.listAdvanceBooking();
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PutMapping("createAdvanceBookingComplete")
    public ResponseEntity<String> createAdvanceBookingComplete(@RequestBody  AdvanceBookingCompleteDtoRequest advanceBookingCompleteDtoRequest)
    {
        return bookingService.createAdvanceBookingComplete(advanceBookingCompleteDtoRequest);
    }

    @GetMapping("getBookingByUserId/{userId}")
    public ResponseEntity<List<BookingDtoResponse>> getBookingByUserId(@PathVariable Long userId)
    {
        return bookingService.getBookingByUserId(userId);
    }

    @SecurityRequirement(name = "Bear Authentication")
    @PutMapping("addDriver/{registrationId}/{bookingId}")
    public ResponseEntity<String> addDriver (@PathVariable int registrationFormId, @PathVariable int bookingId)
    {
        return bookingService.addDriver(registrationFormId, bookingId);
    }

    @GetMapping("totalPrice")
    public ResponseEntity<Float> totalPrice(@RequestBody TotalPriceDtoRequest totalPriceDtoRequest)
    {
        return bookingService.totalPrice(totalPriceDtoRequest);
    }
}

