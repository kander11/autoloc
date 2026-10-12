package tn.esprit.skanderbenmna4cce10.services;

import java.util.List;
import tn.esprit.skanderbenmna4cce10.domain.Employe;

public interface IEmployeService {

    Employe create(Employe employe);

    Employe findById(Long id);

    List<Employe> findAll();

    Employe update(Long id, Employe employe);

    void deleteById(Long id);
}
