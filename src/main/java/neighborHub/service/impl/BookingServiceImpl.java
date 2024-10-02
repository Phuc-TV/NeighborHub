package neighborHub.service.impl;

import neighborHub.model.Entity.Booking;
import neighborHub.model.Entity.FarePrice;
import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.dto.*;
import neighborHub.repository.BookingRepository;
import neighborHub.repository.FarePriceRepository;
import neighborHub.repository.RegistrationFormRepository;
import neighborHub.repository.VoucherRepository;
import neighborHub.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private RegistrationFormRepository registrationFormRepository;

    private static final double EARTH_RADIUS = 6371;

    public BookingServiceImpl (BookingRepository bookingRepository, ModelMapper modelMapper
            ,FarePriceRepository farePriceRepository, VoucherRepository voucherRepository
            ,RegistrationFormRepository registrationFormRepository)
    {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.farePriceRepository = farePriceRepository;
        this.voucherRepository = voucherRepository;
        this.registrationFormRepository = registrationFormRepository;
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
    public ResponseEntity<List<TripCostResponseDto>> calculateFare(TripCostDTO tripCostDTO) {
        try {
            List<FarePrice> farePriceList = farePriceRepository.findAll();
            List<TripCostResponseDto> tripCostResponseDtoList = new ArrayList<>();

            if (farePriceList == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            for (FarePrice farePrice : farePriceList) {
                float totalPrice = 0;
                if (tripCostDTO.getDistance() <= 2000) {
                    totalPrice = farePrice.getFareMin2km();
                } else {
                    totalPrice = farePrice.getFareMin2km()
                            + farePrice.getTravelTimeFare() * (tripCostDTO.getTravelTimeSeconds() / 60f)
                            + farePrice.getFareNextKm() * ((tripCostDTO.getDistance() - 2000) / 1000);
                }

                if (!tripCostDTO.getListVoucher().isEmpty()) {
                    for (int i = 0; i < tripCostDTO.getListVoucher().size(); i++) {
                        float discount = voucherRepository.findByVoucherId(
                                tripCostDTO.getListVoucher().get(i)).orElse(null).getDiscount();
                        totalPrice = totalPrice * (1 - discount / 100);
                    }
                }

                TripCostResponseDto tripCostResponseDto = new TripCostResponseDto();

                tripCostResponseDto.setTripCost(totalPrice);
                tripCostResponseDto.setDistance(tripCostDTO.getDistance() / 1000);
                tripCostResponseDto.setTravelTimeSeconds(tripCostDTO.getTravelTimeSeconds());
                tripCostResponseDto.setVehicleType(farePrice.getVehicleType());

                tripCostResponseDtoList.add(tripCostResponseDto);
            }

            return new ResponseEntity<>(tripCostResponseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<List<DriverResponseDto>> getActiveDriver(DriverActiveRequestDto dto)
    {
        List<RegistrationForm> driverActiveList = registrationFormRepository.getAllByStatus();
        List<RegistrationForm> nearUser = new ArrayList<>();

        for (RegistrationForm registrationForm: driverActiveList)
        {
            double dLat = Math.toRadians(registrationForm.getLat() - dto.getUserLat());
            double dLon = Math.toRadians(registrationForm.getLon() - dto.getUserLon());

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(dto.getUserLat()))
                            * Math.cos(Math.toRadians(registrationForm.getLat())) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            if (EARTH_RADIUS * c <= 2)
            {
                nearUser.add(registrationForm);
            }
        }

        List<DriverResponseDto> driverResponseDtoList = nearUser.stream()
                .map(driverResponseDtoList1 ->
                        modelMapper.map(driverResponseDtoList1, DriverResponseDto.class)).toList();

        return new ResponseEntity<>(driverResponseDtoList, HttpStatus.OK);
    }
}
