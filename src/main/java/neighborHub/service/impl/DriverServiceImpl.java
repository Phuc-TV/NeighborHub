package neighborHub.service.impl;

import neighborHub.model.Entity.Driver;
import neighborHub.model.dto.DriverInfoDtoResponse;
import neighborHub.repository.DriverRepository;
import neighborHub.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DriverServiceImpl(DriverRepository driverRepository, ModelMapper modelMapper)
    {
        this.driverRepository = driverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<DriverInfoDtoResponse> getDriverById(Long id)
    {
        try
        {
            Driver driver = driverRepository.findByDriverId(id).orElse(null);

            if (driver == null)
            {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(modelMapper.map(driver, DriverInfoDtoResponse.class), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
