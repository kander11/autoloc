package tn.esprit.skanderbenmna4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skanderbenmna4cce10.domain.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}