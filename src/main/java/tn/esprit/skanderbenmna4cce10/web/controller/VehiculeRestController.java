package tn.esprit.skanderbenmna4cce10.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.skanderbenmna4cce10.domain.Vehicule;
import tn.esprit.skanderbenmna4cce10.services.IVehiculeService;

@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public Vehicule add(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @GetMapping("{id}")
    public Vehicule getVehicule(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @GetMapping
    public List<Vehicule> getVehicules() {
        return vehiculeService.findAll();
    }

    @PutMapping("{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    @PutMapping("/{vehiculeId}/agence/{agenceId}")
    public Vehicule assignAgence(@PathVariable Long vehiculeId, @PathVariable Long agenceId) {
        return vehiculeService.assignAgence(vehiculeId, agenceId);
    }

    @PutMapping("/{vehiculeId}/equipement/{equipementId}")
    public Vehicule assignEquipement(@PathVariable Long vehiculeId, @PathVariable Long equipementId) {
        return vehiculeService.assignEquipement(vehiculeId, equipementId);
    }
    @DeleteMapping("/{vehiculeId}/equipement/{equipementId}")
    public Vehicule unassignEquipement(@PathVariable Long vehiculeId, @PathVariable Long equipementId) {
        return vehiculeService.unassignEquipement(vehiculeId, equipementId);
    }

    @PostMapping("/agence/{agenceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule addAndAssignAgence(@RequestBody Vehicule vehicule, @PathVariable Long agenceId) {
        return vehiculeService.addAndAssignAgence(vehicule, agenceId);
    }
}