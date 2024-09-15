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

    @Column(nullable = true)
    private int paymentType;

    @Column(nullable = true)
    private String bankNumber;

    @Column(nullable = true)
    private float totalAmount;

    @ManyToOne
    @JoinColumn(name = "driverId", nullable = false)
    private Driver driver;
}

