package api.sebastian.controller;

import api.sebastian.dto.ClienteDTO;
import api.sebastian.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones relacionadas con clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Registra un nuevo cliente con su información básica")
    public ResponseEntity<String> crearCliente(@RequestBody @Valid ClienteDTO dto) {
        clienteService.crearCliente(dto);
        return ResponseEntity.ok("Cliente creado correctamente");
    }
}