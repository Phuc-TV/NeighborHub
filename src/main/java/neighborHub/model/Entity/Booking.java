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
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "registrationId", nullable = false)
    private RegistrationForm registration;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String dropoffLocation;

    @Column(nullable = false)
    private int pickupTime;

    @Column(nullable = false)
    private int dropoffTime;

    @Column(nullable = false)
    private int distance;

    @Column(nullable = false)
    private int status;

    @ManyToMany
    @JoinTable(
            name = "BookingVoucher",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "voucherId")
    )
    private List<Voucher> vouchers;
}
