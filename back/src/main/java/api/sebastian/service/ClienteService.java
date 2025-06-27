package api.sebastian.service;

import api.sebastian.dto.ClienteDTO;
import api.sebastian.model.Cliente;
import api.sebastian.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente crearCliente(ClienteDTO dto) {
        // Verifica si ya existe un cliente con el correo proporcionado
        if (clienteRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("Ya existe un cliente con ese correo.");
        }

        Cliente cliente = Cliente.builder()
                .correo(dto.getCorreo())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .celular(dto.getCelular())
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente obtenerCliente(String correo) {
        return clienteRepository.findById(correo)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con correo: " + correo));
    }
}
