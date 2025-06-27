package api.sebastian.repository;

import api.sebastian.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByCorreo(String correo);
}
