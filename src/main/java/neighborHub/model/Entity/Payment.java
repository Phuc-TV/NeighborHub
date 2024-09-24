package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = true)
    private int paymentTypeId;

    @Column(nullable = true)
    private float paymentAmount;

    @Column(nullable = true)
    private LocalDate paymentDate;

    @Column(nullable = true)
    private String paymentStatus;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private float actualCost;

    @Column(nullable = true)
    private float discountAmount;

    @Column(nullable = true)
    private float finalAmount;

    @Column(nullable = true)
    private String currency;

    @Column(nullable = true)
    private int Status;
}

