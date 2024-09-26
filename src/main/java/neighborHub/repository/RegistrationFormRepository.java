package neighborHub.repository;

import neighborHub.model.Entity.RegistrationForm;
import neighborHub.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Integer> {

    @Query(value = "select * from registration_form where status = true",nativeQuery = true)
    List<RegistrationForm> getAllByStatus();
}
