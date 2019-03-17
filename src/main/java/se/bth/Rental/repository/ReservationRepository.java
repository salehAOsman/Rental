package se.bth.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.bth.Rental.models.Reservation;

@Transactional
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
