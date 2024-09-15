package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucherId;

    @Column(nullable = true)
    private String code;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private float discount;

    @Column(nullable = true)
    private LocalDate expiryDate;

    @Column(nullable = true)
    private boolean status;

    @ManyToMany(mappedBy = "vouchers")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "voucher")
    private List<UserVoucher> userVouchers;
}
