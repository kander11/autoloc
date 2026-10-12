package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;
import tn.esprit.skanderbenmna4cce10.domain.Equipement;

public interface IEquipementService {

    Equipement create(Equipement equipement);

    Equipement findById(Long id);

    List<Equipement> findAll();

    Equipement update(Long id, Equipement equipement);

    void deleteById(Long id);
}
