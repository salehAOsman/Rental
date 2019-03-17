package se.bth.Rental.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bth.Rental.models.Reservation;
import se.bth.Rental.repository.ReservationRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
//@RequestMapping("/apii")//we need this path to fetch by adding to path this word "/apii" as example another methods like GET,PUT ...
public class ReservationController {
    private final Logger log = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private ReservationRepository reservationRepo;

    public ReservationController(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @GetMapping("/reservations")
    Collection<Reservation> reservations() {
        return reservationRepo.findAll();
    }

    @GetMapping("/reservation/{id}")
    ResponseEntity<?> getReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) throws URISyntaxException {
        log.info("Request to create reservation: {}", reservation);
        Reservation result = reservationRepo.save(reservation);
        return ResponseEntity.created(new URI("/api/reservation/" + result.getId())).body(result);
    }

    @PutMapping("/reservation")
    ResponseEntity<Reservation> updateReservation(@Valid @RequestBody Reservation reservation) {
        log.info("Request to update reservation: {}", reservation);
        Reservation result = reservationRepo.save(reservation);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        log.info("Request to delete reservation: {}", id);
        reservationRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
