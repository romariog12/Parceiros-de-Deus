package br.com.romariodev;

import java.util.logging.LogManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication(scanBasePackages={"br.com.romariodev"})
@EnableJpaAuditing
public class Init  extends SpringBootServletInitializer{
	
	 	public static void main(String[] args) {
            SpringApplication.run(Init.class, args);
            // Suppress log messages in stdout.
            LogManager.getLogManager().reset();
          }	
}