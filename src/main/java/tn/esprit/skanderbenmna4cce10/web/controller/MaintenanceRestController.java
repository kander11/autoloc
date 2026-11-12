package tn.esprit.skanderbenmna4cce10.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skanderbenmna4cce10.domain.Maintenance;
import tn.esprit.skanderbenmna4cce10.services.IMaintenanceService;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @GetMapping
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    public Maintenance findById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}