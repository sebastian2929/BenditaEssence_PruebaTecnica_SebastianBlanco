package api.sebastian.service;

import api.sebastian.dto.EntregaDTO;
import api.sebastian.dto.EntregaRespuestaDTO;
import api.sebastian.model.Cliente;
import api.sebastian.model.Entrega;
import api.sebastian.repository.ClienteRepository;
import api.sebastian.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final ClienteRepository clienteRepository;

    public Entrega crearEntrega(EntregaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteCorreo())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        Entrega entrega = Entrega.builder()
                .nombreEntrega(dto.getNombreEntrega())
                .fechaEntrega(dto.getFechaEntrega())
                .fechaCreacion(LocalDateTime.now())
                .cliente(cliente)
                .build();

        return entregaRepository.save(entrega);
    }

    public List<EntregaRespuestaDTO> obtenerPorCorreoConCliente(String correo) {
        List<Entrega> entregas = entregaRepository.findByClienteCorreo(correo);

        return entregas.stream().map(e -> EntregaRespuestaDTO.builder()
                .nombreEntrega(e.getNombreEntrega())
                .fechaEntrega(e.getFechaEntrega())
                .fechaCreacion(e.getFechaCreacion())
                .nombreCliente(e.getCliente().getNombre())
                .celularCliente(e.getCliente().getCelular())
                .build()
        ).toList();
    }
}