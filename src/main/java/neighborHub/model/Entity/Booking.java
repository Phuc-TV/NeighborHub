package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "registrationId", nullable = true)
    private RegistrationForm registration;

    @Column(nullable = true)
    private String pickupLocation;

    @Column(nullable = true)
    private String dropoffLocation;

    @Column(nullable = true)
    private int pickupTime;

    @Column(nullable = true)
    private int dropoffTime;

    @Column(nullable = true)
    private float distance;

    @Column(nullable = true)
    private int status;

    @ManyToMany
    @JoinTable(
            name = "BookingVoucher",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "voucherId")
    )
    private List<Voucher> vouchers;
}
