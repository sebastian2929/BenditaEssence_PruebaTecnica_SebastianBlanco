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
    private final SalesforceService salesforceService;


    public void crearCliente(ClienteDTO dto) {
        if (clienteRepository.existsById(dto.getCorreo())) {
            throw new RuntimeException("El cliente con correo " + dto.getCorreo() + " ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setCelular(dto.getCelular());
        cliente.setCorreo(dto.getCorreo());

        clienteRepository.save(cliente);
        salesforceService.crearLead(cliente); 
    }
}