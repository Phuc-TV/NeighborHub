package neighborHub.repository;

import neighborHub.model.Entity.Booking;
import neighborHub.model.Entity.FareInfo;
import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "select * from booking where status = 'AdvanceBooking'",nativeQuery = true)
    List<Booking> getAdvanceBooking();

    List<Booking> findBookingByUser(User user);


}
