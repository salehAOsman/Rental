package se.bth.Rental.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@ToString( callSuper = true )

@Entity
@PrimaryKeyJoinColumn( referencedColumnName = "id" )
public class Admin extends Person {
    private String roll;

    @Builder
    public Admin(Long id, String firstName, String lastName, String email, String city, List<Reservation> reservation, String roll) {
        super(id, firstName, lastName, email, city, reservation);
        this.roll = roll;
    }

}
