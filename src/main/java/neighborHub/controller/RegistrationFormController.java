package neighborHub.controller;

import neighborHub.model.dto.DriverResponseDto;
import neighborHub.service.RegistrationFormService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

