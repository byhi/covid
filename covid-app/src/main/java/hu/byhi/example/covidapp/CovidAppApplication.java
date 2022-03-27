package hu.byhi.example.covidapp;

import hu.byhi.example.covidapp.service.DatabaseLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CovidAppApplication {
	@Autowired
	DatabaseLoaderService databaseLoaderService;
	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
	}

	@Bean
	public void migaretDataIntoDatabase(){
		databaseLoaderService.initDatabase();
	}
}
