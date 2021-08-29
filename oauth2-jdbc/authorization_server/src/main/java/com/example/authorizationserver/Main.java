package com.example.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	
	/*
	 * curl -X POST -u client1:secret1 'http://localhost:6060/oauth/token?grant_type=client_credentials&amp;scope=read'
	 * GET http://localhost:6060/h2-console
	 */

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
