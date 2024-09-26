package neighborHub.service.impl;

import neighborHub.model.Entity.Booking;
import neighborHub.model.Entity.FarePrice;
import neighborHub.model.dto.BookingDtoResponse;
import neighborHub.model.dto.TripCostDTO;
import neighborHub.model.dto.TripCostResponseDto;
import neighborHub.repository.BookingRepository;
import neighborHub.repository.FarePriceRepository;
import neighborHub.repository.VoucherRepository;
import neighborHub.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FarePriceRepository farePriceRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    public BookingServiceImpl (BookingRepository bookingRepository, ModelMapper modelMapper
            ,FarePriceRepository farePriceRepository, VoucherRepository voucherRepository)
    {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.farePriceRepository = farePriceRepository;
        this.voucherRepository = voucherRepository;
    }

    @Override
    public List<BookingDtoResponse> viewListAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        if (bookingList == null)
            return null;
        List<BookingDtoResponse> bookings = bookingList.stream()
                .map(bookingList1 -> modelMapper.map(bookingList1, BookingDtoResponse.class)).toList();
        return bookings;
    }

    @Override
    public ResponseEntity<TripCostResponseDto> calculateFare(TripCostDTO tripCostDTO) {
        try {
            TripCostResponseDto tripCostResponseDto = new TripCostResponseDto();

            FarePrice farePrice = farePriceRepository
                    .findByVehicleType(tripCostDTO.getVehicleType()).orElse(null);

            if (farePrice == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            float totalPrice = 0;

            if (tripCostDTO.getDistance() <= 2000) {
                totalPrice = farePrice.getFareMin2km();
            } else {
                totalPrice = farePrice.getFareMin2km() + farePrice.getTravelTimeFare()
                        * ((tripCostDTO.getTravelTime().getHour() * 60)
                        + ((float) tripCostDTO.getTravelTime().getMinute()))
                        + farePrice.getFareNextKm() * ((tripCostDTO.getDistance() - 2000) / 1000);
            }

            if (!tripCostDTO.getListVoucher().isEmpty()) {
                for (int i = 0; i < tripCostDTO.getListVoucher().size(); i++) {
                    float discount = voucherRepository.findByVoucherId(
                            tripCostDTO.getListVoucher().get(i)).orElse(null).getDiscount();
                    totalPrice = totalPrice * (1 - discount/100);
                }
            }

            tripCostResponseDto.setTripCost(totalPrice);
            tripCostResponseDto.setDistance(tripCostDTO.getDistance() / 1000);
            tripCostResponseDto.setTravelTime(tripCostDTO.getTravelTime());

            return new ResponseEntity<>(tripCostResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
