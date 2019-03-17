package se.bth.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.AvailableTime;

@Transactional
public interface AvailableTimeRepository extends JpaRepository<AvailableTime,Long> {
}
