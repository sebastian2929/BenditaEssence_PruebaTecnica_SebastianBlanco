package api.sebastian.controller;

import api.sebastian.dto.ClienteDTO;
import api.sebastian.dto.EntregaRespuestaDTO;
import api.sebastian.model.Cliente;
import api.sebastian.service.ClienteService;
import api.sebastian.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final EntregaService entregaService;

    @PostMapping
    public Cliente crearCliente(@RequestBody ClienteDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @GetMapping("/{correo}/entregas")
    public List<EntregaRespuestaDTO> obtenerEntregas(@PathVariable String correo) {
        return entregaService.obtenerPorCorreoConCliente(correo);
    }
}
