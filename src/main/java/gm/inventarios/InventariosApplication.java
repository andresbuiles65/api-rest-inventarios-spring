package gm.inventarios;

import gm.inventarios.modelo.Producto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class InventariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariosApplication.class, args);
	}
}
