package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Driver_payment_method")
public class DriverPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Column(nullable = false)
    private int paymentType;

    @Column(nullable = false)
    private String bankNumber;

    @Column(nullable = false)
    private float totalAmount;

    @ManyToOne
    @JoinColumn(name = "driverId", nullable = false)
    private Driver driver;
}

