package neighborHub.service;

import neighborHub.model.dto.DriverResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistrationFormService {
    ResponseEntity<List<DriverResponseDto>> listDriverActive();
}
