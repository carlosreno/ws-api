package ws.api.wsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class WsApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(WsApiApplication.class, args);

	}

}
