package vttp.paf.day26workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import vttp.paf.day26workshop.repositories.GameRepository;

@SpringBootApplication
public class Day26workshopApplication implements CommandLineRunner {

	@Autowired
	GameRepository gameRepo;

	@Override
	public void run(String... args){
		// gameRepo.getGame(100, 5);
		// System.exit(0);

	}

	public static void main(String[] args) {
		SpringApplication.run(Day26workshopApplication.class, args);
	}

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("/api/bgg/games-ng").allowedOrigins("*");
	// 		}
	// 	};
	// }

}
