package tn.esprit.skanderbenmna4cce10.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skanderbenmna4cce10.domain.Employe;
import tn.esprit.skanderbenmna4cce10.services.IEmployeService;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeRestController {

    private final IEmployeService employeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employe create(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @GetMapping
    public List<Employe> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    public Employe findById(@PathVariable Long id) {
        return employeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}