package neighborHub.service;

import neighborHub.model.dto.DriverInfoDtoResponse;
import org.springframework.http.ResponseEntity;

public interface DriverService {
    ResponseEntity<DriverInfoDtoResponse> getDriverById(Long id);

    ResponseEntity<DriverInfoDtoResponse> getDriverByPhoneNumber(String phoneNumber);
}
