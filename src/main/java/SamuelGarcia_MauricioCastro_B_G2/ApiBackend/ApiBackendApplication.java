package SamuelGarcia_MauricioCastro_B_G2.ApiBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBackendApplication {

	public static void main(String[] args) {
        //Codigo para cargar los valores del archivo .env sobre el archivo application.properties
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
		SpringApplication.run(ApiBackendApplication.class, args);
	}

}
