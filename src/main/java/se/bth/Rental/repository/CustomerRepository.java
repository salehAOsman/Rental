package se.bth.Rental.repository;
import org.springframework.data.repository.query.Param;
import se.bth.Rental.models.Customer;
import se.bth.Rental.models.Person;

public interface CustomerRepository extends PersonBaseRepositories<Customer> {
    @Override
    Person findByFirstName(@Param("firstName") String firstName);
    Person findByLastName(@Param("lastName")String lastName);

}
