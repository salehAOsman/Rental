package se.bth.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import se.bth.Rental.models.RentalResource;

@NoRepositoryBean
public interface ResourceBaseRepositories <T extends RentalResource> extends JpaRepository<T, Long> {

}
