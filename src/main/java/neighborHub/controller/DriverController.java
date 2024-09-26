package neighborHub.controller;

import neighborHub.model.Entity.FareInfo;
import neighborHub.model.dto.DriverInfoDtoResponse;
import neighborHub.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {
    private DriverService driverService;

    public DriverController (DriverService driverService)
    {
        this.driverService = driverService;
    }

    @GetMapping("/viewInfoDriverById/{id}")
    public ResponseEntity<DriverInfoDtoResponse> viewDriverInfoById(@PathVariable long id){
        return driverService.getDriverById(id);
    }
}
