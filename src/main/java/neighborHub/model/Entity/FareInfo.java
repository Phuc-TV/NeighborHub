package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fare_Info")
public class FareInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fareInfoId;

    @Column(nullable = true)
    private int commissionRate;

    @Column(nullable = true)
    private int vatRate;

    @Column(nullable = true)
    private String Twilio_Account_sid;

    @Column(nullable = true)
    private String Twilio_Auth_Token;

    @Column(nullable = true)
    private String Twilio_VerifyService_Sid;
}

