package com.ycjw.minesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableSpringDataWebSupport
@EnableSwagger2
public class Booter {
	public static void main(String[] args) {
		SpringApplication.run(Booter.class, args);
	}

}
