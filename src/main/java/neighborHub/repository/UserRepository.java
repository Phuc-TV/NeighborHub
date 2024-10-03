package neighborHub.repository;

import neighborHub.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneOrEmail(String phone, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);

    Optional<User> findByUserId(Long userId);

    @Query(value = "select * from User",nativeQuery = true)
    List<User> getAll();
}
