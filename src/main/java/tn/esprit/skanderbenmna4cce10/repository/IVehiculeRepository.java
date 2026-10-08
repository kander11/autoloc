package tn.esprit.skanderbenmna4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}