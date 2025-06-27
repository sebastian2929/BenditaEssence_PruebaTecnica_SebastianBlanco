package api.sebastian.controller;

import api.sebastian.dto.EntregaDTO;
import api.sebastian.model.Entrega;
import api.sebastian.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
@Tag(name = "Entregas", description = "Gestión de entregas para los clientes")
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    @Operation(summary = "Crear entrega", description = "Crea una entrega asociada a un cliente existente")
    public ResponseEntity<Entrega> crearEntrega(@RequestBody Entrega entrega) {
        Entrega nueva = entregaService.crearEntrega(entrega);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping("/cliente/{correo}")
    @Operation(summary = "Listar entregas por correo", description = "Obtiene todas las entregas de un cliente usando su correo")
    public ResponseEntity<List<EntregaDTO>> obtenerEntregasPorCorreo(@PathVariable String correo) {
        List<EntregaDTO> entregas = entregaService.obtenerEntregasPorCorreoCliente(correo);
        return ResponseEntity.ok(entregas);
    }
}