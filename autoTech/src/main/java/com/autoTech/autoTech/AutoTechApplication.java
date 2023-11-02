package com.autoTech.autoTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.autoTech.autoTech")
public class AutoTechApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutoTechApplication.class, args);
	}

}
