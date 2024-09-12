package neighborHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import neighborHub.model.Entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String refreshToken);
}