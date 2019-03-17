package se.bth.Rental.models;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true ) //to fetch some fields from parent
@ToString( callSuper = true ) //to call super field

@Entity//ti create table in database
@PrimaryKeyJoinColumn(referencedColumnName = "id") //table related by id from parent personController
public class Customer extends Person {
    private CustomerStatus status;
    private String payCard;

    @Builder//I use here builder not over class because I have inherited class to call super class from constructor
    public Customer(Long id, String firstName, String lastName, String email, String city, List<Reservation> reservation, CustomerStatus status, String payCard) {
        super(id, firstName, lastName, email, city, reservation);
        this.status = status;
        this.payCard = payCard;
    }
}