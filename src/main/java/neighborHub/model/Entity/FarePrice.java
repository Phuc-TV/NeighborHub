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
@Table(name = "Fare_Price")
public class FarePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fareId;

    @Column(nullable = true)
    private String vehicleType;

    @Column(nullable = true)
    private float fareMin2km;

    @Column(nullable = true)
    private float fareNextKm;

    @Column(nullable = true)
    private float travelTimeFare;

    @Column(nullable = true)
    private LocalDate updateDate;

    @Column(nullable = true)
    private String updateBy;
}

