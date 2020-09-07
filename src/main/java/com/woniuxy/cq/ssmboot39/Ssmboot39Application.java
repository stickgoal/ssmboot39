package com.woniuxy.cq.ssmboot39;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Ssmboot39Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssmboot39Application.class, args);
	}

}
