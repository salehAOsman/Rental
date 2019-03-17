package se.bth.Rental.repository;


import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.RentalResource;

@Transactional
public interface ResourceRepository  extends ResourceBaseRepositories<RentalResource> {

}
