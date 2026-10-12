package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;
import tn.esprit.skanderbenmna4cce10.domain.Reservation;

public interface IReservationService {

    Reservation create(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    Reservation update(Long id, Reservation reservation);

    void deleteById(Long id);
}
