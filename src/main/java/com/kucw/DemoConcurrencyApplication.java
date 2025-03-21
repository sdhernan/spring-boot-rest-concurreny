package com.kucw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class DemoConcurrencyApplication.
 */
@SpringBootApplication
@EnableScheduling
public class DemoConcurrencyApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoConcurrencyApplication.class, args);
	}
}
