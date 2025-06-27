package api.sebastian.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String celular;

    @Email
    @NotBlank
    private String correo;
}