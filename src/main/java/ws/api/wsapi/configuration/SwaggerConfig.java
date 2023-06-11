package ws.api.wsapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Api Rasmoo",
                version = "1.0",
                contact = @Contact(
                        name = "Carlos Davi Alves Ferreira",
                        email = "carlosdavi090787@gmail.com",
                        url = "https://www.linkedin.com/in/carlos-d-javs/"
                ),
                license = @License(
                        name = "licence"
                )
        )
)
public class SwaggerConfig {
//    @Bean
//    public OpenAPI api(){
//        return new OpenAPI(SpecVersion.V30);
//    }

}
