package neighborHub.repository;

import neighborHub.model.Entity.FarePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarePriceRepository extends JpaRepository<FarePrice, Integer> {
    Optional<FarePrice> findByVehicleType(String vehicleType);
}
