package neighborHub.service.impl;

import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.dto.DriverResponseDto;
import neighborHub.model.dto.RegistrationFormRequestDto;
import neighborHub.model.dto.RegistrationFormResponseDto;
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

    @Override
    public ResponseEntity<String> isActiveDriver(RegistrationFormRequestDto dto)
    {
        try {
            RegistrationForm registrationForm = repository.getRegistrationFormById(dto.getRegistrationId());

            registrationForm.setStatus(1);
            registrationForm.setLat(dto.getLat());
            registrationForm.setLon(dto.getLon());

            repository.save(registrationForm);

            return new ResponseEntity<>("Is Active",HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> unActiveDriver(int id)
    {
        try {
            RegistrationForm registrationForm = repository.getRegistrationFormById(id);

            registrationForm.setStatus(0);
            registrationForm.setLat(0);
            registrationForm.setLon(0);

            repository.save(registrationForm);

            return new ResponseEntity<>("UnActive",HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<RegistrationFormResponseDto>> getRegistrationFormByDriverId(Long driverId)
    {
        List<RegistrationForm> registrationFormList = repository.getAllByDriverId(driverId);

        List<RegistrationFormResponseDto> responseDtoList = registrationFormList.stream()
                .map(registrationFormList1 ->
                        modelMapper.map(registrationFormList1, RegistrationFormResponseDto.class)).toList();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
}
