package api.sebastian.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class EntregaRespuestaDTO {
    private String nombreEntrega;
    private LocalDate fechaEntrega;
    private LocalDateTime fechaCreacion;
    private String nombreCliente;
    private String celularCliente;
}