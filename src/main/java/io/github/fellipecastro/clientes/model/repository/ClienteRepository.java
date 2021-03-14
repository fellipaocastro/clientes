package io.github.fellipecastro.clientes.model.repository;

import io.github.fellipecastro.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
