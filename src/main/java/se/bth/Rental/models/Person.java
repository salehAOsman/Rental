package se.bth.Rental.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance( strategy = InheritanceType.JOINED )//we need it if we have inherit from this class to child
public abstract class Person {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String city;

    @OneToMany(fetch = FetchType.LAZY)  //Fetch all information from customer
    private List<Reservation> reservation;
}