package neighborHub.repository;

import neighborHub.model.Entity.User;
import neighborHub.model.Entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    Optional<Voucher> findByVoucherId(int id);
}
