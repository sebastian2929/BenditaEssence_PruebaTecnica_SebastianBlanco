package api.sebastian.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    private String correo;

    private String nombre;
    private String apellido;
    private String celular;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Entrega> entregas;
}
