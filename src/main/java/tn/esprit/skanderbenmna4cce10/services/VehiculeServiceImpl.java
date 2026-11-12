package tn.esprit.skanderbenmna4cce10.services;

import java.math.BigDecimal;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import tn.esprit.skanderbenmna4cce10.domain.Agence;
import tn.esprit.skanderbenmna4cce10.domain.Equipement;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;
import tn.esprit.skanderbenmna4cce10.exception.ResourceNotFoundException;
import tn.esprit.skanderbenmna4cce10.repository.IAgenceRepository;
import tn.esprit.skanderbenmna4cce10.repository.IEquipementRepository;
import tn.esprit.skanderbenmna4cce10.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existant = findById(id);
        verifier(vehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule assignAgence(Long vehiculeId, Long agenceId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", vehiculeId));

        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", agenceId));

        vehicule.setAgence(agence);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule assignEquipement(Long vehiculeId, Long equipementId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", vehiculeId));

        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", equipementId));

        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeRepository.save(vehicule);
    }

    private void verifier(Vehicule vehicule) {
        BigDecimal tarif = vehicule.getTarifJournalier();
        if (tarif != null && tarif.signum() < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }

    @Override
    public Vehicule unassignEquipement(Long vehiculeId, Long equipementId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", vehiculeId));

        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", equipementId));

        if (!vehicule.getEquipements().remove(equipement)) {
            throw new IllegalArgumentException("Cet équipement n'est pas affecté à ce véhicule");
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    // quand on fait commit on peut pas faire rollback , ca permet de assurer le rollback
    public Vehicule addAndAssignAgence(Vehicule vehicule, Long agenceId) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);

        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", agenceId));

        vehicule.setAgence(agence);

        // La cascade enregistre aussi les équipements du véhicule
        return vehiculeRepository.save(vehicule);
    }

}
