package se.bth.Rental.repository;
import se.bth.Rental.models.Person;

import javax.transaction.Transactional;


@Transactional // here it inheret from PersonBaseRepositories that extend from jpaRepository
public interface PersonRepository extends PersonBaseRepositories<Person>{

}
