package com.glc.bookservice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookServiceApplication {

	@Bean
    public Queue hello(){
       return new Queue("book"); 
    }

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
