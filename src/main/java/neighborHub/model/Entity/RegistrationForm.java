package neighborHub.model.Entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Registration_Form")
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationId;

    @Column(nullable = false)
    private String driversLicenseNumber;

    @Column(nullable = false)
    private String driversLicenseImgFront;

    @Column(nullable = false)
    private String driversLicenseImgBack;

    @Column(nullable = false)
    private String lltpImg;

    @Column(nullable = false)
    private String vehicleRegistrationImg;

    @Column(nullable = false)
    private LocalDate healthCheckDay;

    @Column(nullable = false)
    private String vehicleInsuranceImgFront;

    @Column(nullable = false)
    private String vehicleInsuranceImgBack;

    @Column(nullable = false)
    private String tin;

    @ManyToOne
    @JoinColumn(name = "driverId", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    private int totalStar;

    @Column(nullable = false)
    private int status;
}

