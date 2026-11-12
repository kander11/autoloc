package tn.esprit.skanderbenmna4cce10.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skanderbenmna4cce10.domain.Paiement;
import tn.esprit.skanderbenmna4cce10.services.IPaiementService;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paiement create(@RequestBody Paiement paiement) {
        return paiementService.create(paiement);
    }

    @GetMapping
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    public Paiement findById(@PathVariable Long id) {
        return paiementService.findById(id);
    }

    @PutMapping("/{id}")
    public Paiement update(@PathVariable Long id, @RequestBody Paiement paiement) {
        return paiementService.update(id, paiement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        paiementService.deleteById(id);
    }
}