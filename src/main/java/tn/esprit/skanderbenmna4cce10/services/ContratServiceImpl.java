package tn.esprit.skanderbenmna4cce10.services;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.skanderbenmna4cce10.domain.Contrat;
import tn.esprit.skanderbenmna4cce10.exception.ResourceNotFoundException;
import tn.esprit.skanderbenmna4cce10.repository.IContratRepository;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.skanderbenmna4cce10.domain.Paiement;
import tn.esprit.skanderbenmna4cce10.domain.Reservation;
import tn.esprit.skanderbenmna4cce10.repository.IReservationRepository;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        verifier(contrat);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        Contrat existant = findById(id);
        verifier(contrat);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Contrat addWithPayments(Contrat c) {
        if (c.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (c.getMontantTotal() == null || c.getMontantTotal().signum() <= 0) {
            throw new IllegalArgumentException("Le montant total doit être strictement positif");
        }

        // 1. La réservation doit exister
        if (c.getReservation() == null || c.getReservation().getIdReservation() == null) {
            throw new IllegalArgumentException("Le contrat doit référencer une réservation existante");
        }
        Long reservationId = c.getReservation().getIdReservation();
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", reservationId));
        c.setReservation(reservation);

        // 2. Contrôle et rattachement des paiements
        BigDecimal somme = BigDecimal.ZERO;
        if (c.getPaiements() != null) {
            for (Paiement p : c.getPaiements()) {
                if (p.getIdPaiement() != null) {
                    throw new IllegalArgumentException("Un nouveau paiement ne doit pas avoir d'identifiant");
                }
                if (p.getMontant() == null || p.getMontant().signum() <= 0) {
                    throw new IllegalArgumentException("Le montant d'un paiement doit être strictement positif");
                }
                p.setContrat(c);              // côté propriétaire
                somme = somme.add(p.getMontant());
            }
        }
        if (somme.compareTo(c.getMontantTotal()) > 0) {
            throw new IllegalArgumentException("La somme des paiements dépasse le montant total du contrat");
        }

        // 3. Un seul save : la cascade enregistre les paiements
        return contratRepository.save(c);
    }

    private void verifier(Contrat contrat) {
        BigDecimal montant = contrat.getMontantTotal();
        if (montant != null && montant.signum() < 0) {
            throw new IllegalArgumentException("Le montant total d'un contrat ne peut pas être négatif");
        }
    }
}
