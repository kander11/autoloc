package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;

public interface IVehiculeService {

    Vehicule create(Vehicule vehicule);

    Vehicule findById(Long id);

    List<Vehicule> findAll();

    Vehicule update(Long id, Vehicule vehicule);

    void deleteById(Long id);
}
