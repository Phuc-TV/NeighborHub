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
    private int paymentId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private int paymentTypeId;

    @Column(nullable = false)
    private float paymentAmount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float actualCost;

    @Column(nullable = false)
    private float discountAmount;

    @Column(nullable = false)
    private float finalAmount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int Status;
}

