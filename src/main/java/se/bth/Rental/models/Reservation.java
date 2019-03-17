package se.bth.Rental.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationTime = LocalDateTime.now();
    private ReservationStatus status = ReservationStatus.RESERVED;

    @OneToMany( fetch = FetchType.LAZY)
    private List<AvailableTime> rentalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentalResource borrowedStuff;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer renter;
}
