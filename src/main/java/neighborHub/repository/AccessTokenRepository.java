package neighborHub.repository;

import neighborHub.model.Entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
    @Query("""
                        select t from AccessToken t
                        inner join User u on u.userId = t.user.userId
                        where u.userId = :userId and (t.expired = false or t.revoked = false)
            """)
    List<AccessToken> findAllValidTokensByUser(long userId);

    AccessToken findByToken(String accessToken);
}
