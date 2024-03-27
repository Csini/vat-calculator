package hu.spring.feladat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class VatCalculatorBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(VatCalculatorBusinessApplication.class, args);
	}

}
