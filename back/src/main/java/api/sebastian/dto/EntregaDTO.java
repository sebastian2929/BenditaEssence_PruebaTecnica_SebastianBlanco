package api.sebastian.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntregaDTO {
    private String nombreEntrega;
    private LocalDate fechaEntrega;
    private String clienteCorreo;
}

