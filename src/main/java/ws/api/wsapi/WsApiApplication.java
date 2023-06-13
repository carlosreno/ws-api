package ws.api.wsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@ComponentScan(basePackages = "ws.api.wsapi.integration")
@SpringBootApplication
public class WsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApiApplication.class, args);
	}

}
