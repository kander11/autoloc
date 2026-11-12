package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;

import tn.esprit.skanderbenmna4cce10.domain.Agence;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;

public interface IVehiculeService {

    Vehicule create(Vehicule vehicule);

    Vehicule findById(Long id);

    List<Vehicule> findAll();

    Vehicule update(Long id, Vehicule vehicule);

    void deleteById(Long id);
    Vehicule assignAgence(Long vehiculeId, Long agenceId);

    Vehicule assignEquipement(Long vehiculeId, Long equipementId);
    Vehicule unassignEquipement(Long vehiculeId, Long equipementId);
    Vehicule addAndAssignAgence(Vehicule vehicule, Long agenceId);
}
