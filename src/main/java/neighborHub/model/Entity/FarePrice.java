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

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private float fareMin2km;

    @Column(nullable = false)
    private float fareNextKm;

    @Column(nullable = false)
    private float travelTimeFare;

    @Column(nullable = false)
    private LocalDate updateDate;

    @Column(nullable = false)
    private String updateBy;
}

