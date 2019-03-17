package se.bth.Rental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double pricePerHour;

    private LocalDateTime registerDateTime;
    private ResourceStatus status;
    private Integer quantity = 1;//many chairs as example
    private String description;
    private Category category;

    @OneToMany( fetch = FetchType.LAZY )
    private List<AvailableTime> availableTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer owner;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Reservation> reservations;//I need it because some times each item relates by deference owners
}
