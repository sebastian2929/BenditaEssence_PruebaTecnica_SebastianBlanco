package api.sebastian.controller;

import api.sebastian.dto.EntregaDTO;
import api.sebastian.dto.EntregaRespuestaDTO;
import api.sebastian.model.Entrega;
import api.sebastian.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    public Entrega crearEntrega(@RequestBody EntregaDTO entregaDTO) {
        return entregaService.crearEntrega(entregaDTO);
    }

    @GetMapping("/cliente/{correo}")
    public List<EntregaRespuestaDTO> entregasPorCliente(@PathVariable String correo) {
        return entregaService.obtenerPorCorreoConCliente(correo);
    }
}
