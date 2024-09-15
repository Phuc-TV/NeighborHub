package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Column(nullable = true)
    private String paymentType;

    @Column(nullable = true)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;
}

