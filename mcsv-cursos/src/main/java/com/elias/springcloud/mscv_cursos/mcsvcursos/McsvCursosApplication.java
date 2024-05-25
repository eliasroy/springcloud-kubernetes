package com.elias.springcloud.mscv_cursos.mcsvcursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class McsvCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvCursosApplication.class, args);
	}

}
