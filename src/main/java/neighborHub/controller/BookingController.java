package neighborHub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import neighborHub.model.dto.BookingDtoResponse;
import neighborHub.model.dto.TripCostDTO;
import neighborHub.model.dto.TripCostResponseDto;
import neighborHub.model.dto.VoucherDtoResponse;
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
    public ResponseEntity<TripCostResponseDto> calculateFare(@RequestBody TripCostDTO tripCostDTO) {
        return bookingService.calculateFare(tripCostDTO);
    }

}

