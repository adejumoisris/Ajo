package com.AjoPay.AjoPay;

import com.AjoPay.AjoPay.service.serviceImplementation.SendMailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AjoPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjoPayApplication.class, args);
	}




}
