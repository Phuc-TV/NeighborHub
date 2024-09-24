package neighborHub.service.impl;

import neighborHub.model.Entity.Booking;
import neighborHub.model.Entity.FarePrice;
import neighborHub.model.dto.BookingDtoResponse;
import neighborHub.model.dto.TripCostDTO;
import neighborHub.repository.BookingRepository;
import neighborHub.repository.FarePriceRepository;
import neighborHub.repository.VoucherRepository;
import neighborHub.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public float calculateFare(TripCostDTO tripCostDTO) {
        try {
            FarePrice farePrice = farePriceRepository
                    .findByVehicleType(tripCostDTO.getVehicleType()).orElse(null);

            if (farePrice == null)
                return 0;

            float totalPrice = 0;

            if (tripCostDTO.getDistance() <= 2) {
                totalPrice = farePrice.getFareMin2km();
            } else {
                totalPrice = farePrice.getFareMin2km() + farePrice.getTravelTimeFare()
                        * (tripCostDTO.getTravelTime().getHour()
                        + ((float) tripCostDTO.getTravelTime().getMinute() / 60))
                        + farePrice.getFareNextKm() * (tripCostDTO.getDistance() - 2);
            }

            if (!tripCostDTO.getListVoucher().isEmpty()) {
                for (int i = 0; i < tripCostDTO.getListVoucher().size(); i++) {
                    float discount = voucherRepository.findByVoucherId(
                            tripCostDTO.getListVoucher().get(i)).orElse(null).getDiscount();
                    totalPrice = totalPrice * (1 - discount/100);
                }
            }

            return totalPrice;
        } catch (Exception e) {
            return -1;
        }
    }
}
