package com.rahdevelopers.api.transacciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioTransaccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioTransaccionesApplication.class, args);
	}

}
