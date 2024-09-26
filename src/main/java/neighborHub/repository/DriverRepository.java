package neighborHub.repository;

import neighborHub.model.Entity.Driver;
import neighborHub.model.Entity.FareInfo;
import neighborHub.model.Entity.FarePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByDriverId(Long id);
}
