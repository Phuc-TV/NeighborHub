package neighborHub.service.impl;

import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.dto.DriverResponseDto;
import neighborHub.repository.RegistrationFormRepository;
import neighborHub.service.RegistrationFormService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {
    @Autowired
    private RegistrationFormRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RegistrationFormServiceImpl (RegistrationFormRepository repository, ModelMapper modelMapper)
    {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<List<DriverResponseDto>> listDriverActive()
    {
        try
        {
            List<RegistrationForm> drivers = repository.getAllByStatus();

            if (drivers == null)
            {
                return null;
            }

            List<DriverResponseDto> driverList = drivers.stream()
                    .map(driverList1 -> modelMapper.map(driverList1, DriverResponseDto.class)).toList();
            return new ResponseEntity<>(driverList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
