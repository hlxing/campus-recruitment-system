package com.hlx.pagesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PagesServerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PagesServerApplication.class, args);
	}
}
