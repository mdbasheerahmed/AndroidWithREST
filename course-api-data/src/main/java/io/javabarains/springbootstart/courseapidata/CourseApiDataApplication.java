package io.javabarains.springbootstart.courseapidata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiDataApplication {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 8085 );
		SpringApplication.run(CourseApiDataApplication.class, args);
	}
}