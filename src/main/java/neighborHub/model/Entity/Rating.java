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
        @JoinColumn(name = "bookingId", nullable = true)
        private Booking booking;

        @Column(nullable = true)
        private int userRating;

        @Column(nullable = true)
        private String userComment;

        @Column(nullable = true)
        private int driverRating;

        @Column(nullable = true)
        private String driverComment;
    }
