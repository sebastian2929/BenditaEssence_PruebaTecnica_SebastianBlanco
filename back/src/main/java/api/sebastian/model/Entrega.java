package api.sebastian.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEntrega;
    private LocalDate fechaEntrega;
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "cliente_correo")
    @JsonBackReference
    private Cliente cliente;
}

