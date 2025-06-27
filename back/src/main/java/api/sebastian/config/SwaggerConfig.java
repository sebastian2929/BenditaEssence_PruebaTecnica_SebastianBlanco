package api.sebastian.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("API de Clientes y Entregas")
                        .description("Documentación de API REST para gestión de clientes y entregas")
                        .version("1.0"));
    }
}
