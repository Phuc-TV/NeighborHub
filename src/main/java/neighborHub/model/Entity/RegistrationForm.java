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

    @Column(nullable = true)
    private String licensePlate;

    @Column(nullable = true)
    private String vehicleType;

    @Column(nullable = true)
    private String driversLicenseNumber;

    @Column(nullable = true)
    private String driversLicenseImgFront;

    @Column(nullable = true)
    private String driversLicenseImgBack;

    @Column(nullable = true)
    private String lltpImg;

    @Column(nullable = true)
    private String vehicleRegistrationImg;

    @Column(nullable = true)
    private LocalDate healthCheckDay;

    @Column(nullable = true)
    private String vehicleInsuranceImgFront;

    @Column(nullable = true)
    private String vehicleInsuranceImgBack;

    @Column(nullable = true)
    private String tin;

    @Column(nullable = true)
    private double lat;

    @Column(nullable = true)
    private double lon;

    @ManyToOne
    @JoinColumn(name = "driverId", nullable = false)
    private Driver driver;

    @Column(nullable = true)
    private int totalStar;

    @Column(nullable = true)
    private int status;
}

