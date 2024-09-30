package neighborHub.service;

import neighborHub.model.dto.DriverResponseDto;
import neighborHub.model.dto.RegistrationFormRequestDto;
import neighborHub.model.dto.RegistrationFormResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistrationFormService {
    ResponseEntity<List<DriverResponseDto>> listDriverActive();

    ResponseEntity<String> isActiveDriver(RegistrationFormRequestDto dto);

    ResponseEntity<String> unActiveDriver(int id);

    ResponseEntity<List<RegistrationFormResponseDto>> getRegistrationFormByDriverId(Long driverId);
}
