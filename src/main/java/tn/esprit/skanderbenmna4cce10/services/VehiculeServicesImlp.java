package tn.esprit.skanderbenmna4cce10.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;
import tn.esprit.skanderbenmna4cce10.repository.IVehiculeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class VehiculeServicesImlp implements IVehiculeservices {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Véhicule introuvable : " + id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        vehiculeRepository.deleteById(id);
    }
}