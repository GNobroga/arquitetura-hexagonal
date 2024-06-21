package br.com.gabriel.hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HexagonalApplication {


	public static void main(String[] args) {
		SpringApplication.run(HexagonalApplication.class, args);
	}

}
