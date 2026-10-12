package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;
import tn.esprit.skanderbenmna4cce10.domain.Agence;

public interface IAgenceService {

    Agence create(Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Long id, Agence agence);

    void deleteById(Long id);
}
