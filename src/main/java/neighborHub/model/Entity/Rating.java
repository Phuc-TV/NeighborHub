package neighborHub.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private int userRating;

    @Column(nullable = false)
    private String userComment;

    @Column(nullable = false)
    private int driverRating;

    @Column(nullable = false)
    private String driverComment;
}
