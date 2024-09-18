package neighborHub.service.impl;

import neighborHub.repository.BookingRepository;
import neighborHub.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookingServiceImpl (BookingRepository bookingRepository, ModelMapper modelMapper)
    {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }


}
