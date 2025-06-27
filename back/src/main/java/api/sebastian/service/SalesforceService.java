package api.sebastian.service;

import api.sebastian.exception.DuplicadoSalesforceException;
import api.sebastian.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SalesforceService {

    @Value("${salesforce.login.url}")
    private String loginUrl;

    @Value("${salesforce.client.id}")
    private String clientId;

    @Value("${salesforce.client.secret}")
    private String clientSecret;

    @Value("${salesforce.username}")
    private String username;

    @Value("${salesforce.password}")
    private String password;

    @Value("${salesforce.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    public void crearLead(Cliente cliente) {
        log.info("🔐 Iniciando autenticación con Salesforce...");

        String tokenUrl = loginUrl + "/services/oauth2/token";

        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("username", username);
        params.add("password", password + token);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, tokenHeaders);

        Map<String, Object> tokenResponse = restTemplate.postForObject(tokenUrl, tokenRequest, Map.class);
        if (tokenResponse == null || !tokenResponse.containsKey("access_token") || !tokenResponse.containsKey("instance_url")) {
            log.error("❌ No se recibió token de Salesforce. Respuesta: {}", tokenResponse);
            throw new RuntimeException("Error en la autenticación con Salesforce");
        }

        String accessToken = (String) tokenResponse.get("access_token");
        String instanceUrl = (String) tokenResponse.get("instance_url");

        log.info("✅ Autenticación exitosa. AccessToken recibido.");

        String leadUrl = instanceUrl + "/services/data/v58.0/sobjects/Lead";

        HttpHeaders leadHeaders = new HttpHeaders();
        leadHeaders.setContentType(MediaType.APPLICATION_JSON);
        leadHeaders.setBearerAuth(accessToken);

        Map<String, String> leadBody = new HashMap<>();
        leadBody.put("FirstName", cliente.getNombre());
        leadBody.put("LastName", cliente.getApellido());
        leadBody.put("Email", cliente.getCorreo());
        leadBody.put("Phone", cliente.getCelular());
        // Salesforce requires Company field; usando valor por defecto
        leadBody.put("Company", "N/A");

        HttpEntity<Map<String, String>> leadRequest = new HttpEntity<>(leadBody, leadHeaders);

        log.info("🚀 Enviando Lead a Salesforce para el cliente: {}", cliente.getCorreo());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(leadUrl, leadRequest, String.class);
            log.info("✅ Lead creado exitosamente en Salesforce.");
            log.debug("Respuesta completa: {}", response.getBody());
        } catch (HttpClientErrorException e) {
            log.error("❌ Error al crear Lead en Salesforce. Código: {}, Detalles: {}",
                    e.getStatusCode(), e.getResponseBodyAsString());

            if (e.getStatusCode() == HttpStatus.BAD_REQUEST &&
                e.getResponseBodyAsString().contains("DUPLICATES_DETECTED")) {
                throw new DuplicadoSalesforceException("El correo ya se encuentra registrado en Salesforce.");
            } else {
                throw new RuntimeException("Error al crear el Lead en Salesforce: " + e.getMessage());
            }
        }
    }
}
