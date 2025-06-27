package api.sebastian.service;

import api.sebastian.dto.EntregaDTO;
import api.sebastian.model.Cliente;
import api.sebastian.model.Entrega;
import api.sebastian.repository.ClienteRepository;
import api.sebastian.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregaService {
    private final ClienteRepository clienteRepository;
    private final EntregaRepository entregaRepository;

    public Entrega crearEntrega(Entrega entrega) {
        Cliente cliente = clienteRepository.findByCorreo(entrega.getClienteCorreo())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con correo: " + entrega.getClienteCorreo()));

        entrega.setCliente(cliente);
        return entregaRepository.save(entrega);
    }

    public List<EntregaDTO> obtenerEntregasPorCorreoCliente(String correo) {
        List<Entrega> entregas = entregaRepository.findByCliente_Correo(correo);

        return entregas.stream().map(e -> {
            EntregaDTO dto = new EntregaDTO();
            dto.setNombreEntrega(e.getNombreEntrega());
            dto.setFechaEntrega(e.getFechaEntrega());
            dto.setClienteCorreo(e.getCliente().getCorreo());
            dto.setNombreCliente(e.getCliente().getNombre() + " " + e.getCliente().getApellido());
            dto.setCelularCliente(e.getCliente().getCelular());
            return dto;
        }).collect(Collectors.toList());
    }
}