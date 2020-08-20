package com.rahdevelopers.api.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringbootServicioConfiglServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioConfiglServerApplication.class, args);
	}

}
