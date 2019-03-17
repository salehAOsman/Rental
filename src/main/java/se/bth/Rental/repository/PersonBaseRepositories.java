package se.bth.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.bth.Rental.models.Person;

@NoRepositoryBean // it is abstract
public interface PersonBaseRepositories <T extends Person> extends JpaRepository<T,Long> {

    Person findByFirstName(String name);

}
