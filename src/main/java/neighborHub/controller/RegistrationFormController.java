package neighborHub.controller;

import neighborHub.model.dto.DriverResponseDto;
import neighborHub.model.dto.RegistrationFormRequestDto;
import neighborHub.model.dto.RegistrationFormResponseDto;
import neighborHub.service.RegistrationFormService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrationForm")
public class RegistrationFormController {
    @Autowired
    private RegistrationFormService service;

    @Autowired
    public RegistrationFormController (RegistrationFormService service)
    {
        this.service = service;
    }

    @GetMapping("/getAllDriverActive")
    public ResponseEntity<List<DriverResponseDto>> getAllDriverActive()
    {
        return service.listDriverActive();
    }

    @GetMapping("/getAllRegistrationForm/{id}")
    public ResponseEntity<List<RegistrationFormResponseDto>> getAllByDriverId(@PathVariable long id)
    {
        return service.getRegistrationFormByDriverId(id);
    }

    @PutMapping("/isActive")
    public ResponseEntity<String> isActive(@RequestBody RegistrationFormRequestDto dto)
    {
        return service.isActiveDriver(dto);
    }

    @PutMapping("/unActive/{id}")
    public ResponseEntity<String> unActive(@PathVariable int id)
    {
        return service.unActiveDriver(id);
    }
}

