package tn.esprit.skanderbenmna4cce10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.skanderbenmna4cce10.domain.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
}