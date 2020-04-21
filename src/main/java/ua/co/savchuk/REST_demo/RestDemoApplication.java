package ua.co.savchuk.REST_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;
import ua.co.savchuk.REST_demo.model.Wizard;

@SpringBootApplication
public class RestDemoApplication {
	private static final Logger log = LoggerFactory.getLogger(RestDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


//	Uncomment for insert data to H2 database

//	@Bean
//	public CommandLineRunner demo(WizardsRepository repository) {
//		return (args) -> {
//			repository.save(new Wizard("Harry Potter", 17, Spell.ExpectoPatronum));
//			repository.save(new Wizard("Germiona Grenger", 18, Spell.WingardiumLeviosa));
//			repository.save(new Wizard("Severus Snape", 38, Spell.AvadaKedavra, Spell.ExpectoPatronum));
//			repository.save(new Wizard("Albus Dambldor", 116, Spell.values()));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Wizard wizard : repository.findAll()) {
//				log.info(wizard.toString());
//			}
//		};
//	}
}
