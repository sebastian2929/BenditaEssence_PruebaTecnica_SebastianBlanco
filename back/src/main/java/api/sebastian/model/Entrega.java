package api.sebastian.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEntrega;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_correo")
    private Cliente cliente;

    @Transient
    private String clienteCorreo;

    @PostLoad
    @PostPersist
    private void setTransientCorreo() {
        if (cliente != null) {
            this.clienteCorreo = cliente.getCorreo();
        }
    }
}
