package com.fanatics;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BulletinApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulletinApplication.class, args);
	}

	@Bean
	public Queue bookerBetsQueue() {
		return new Queue("bookerBetsQueue", false);
	}



}
