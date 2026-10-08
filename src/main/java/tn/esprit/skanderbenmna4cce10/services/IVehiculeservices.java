package tn.esprit.skanderbenmna4cce10.services;

import tn.esprit.skanderbenmna4cce10.domain.Vehicule;

import java.util.List;

public interface IVehiculeservices {

    Vehicule create(Vehicule vehicule);

    Vehicule findById(Long id);

    List<Vehicule> findAll();

    void delete(Long id);
}