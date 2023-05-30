package by.fmpibsu.stogram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@EnableRedisHttpSession
@SpringBootApplication
@Slf4j
public class Application {

//	private static final Logger logger = LoggerFactory.getLogger(Application.class);
//	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			log.info("Entering application");
			System.out.println("Server running!");
		};
	}
}
