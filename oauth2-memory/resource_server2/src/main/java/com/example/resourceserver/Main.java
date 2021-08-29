package com.example.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	/*
	 * GIT BASH curl -v -XPOST -u client1:secret1'http://localhost:6060/oauth/token?grant_type=client_credentials&username=john&password=12345&scope=read'
	 * GET http://localhost:8080/test BEARER TOKEN (REQUEST RETURN FORBIDDEN - PRE AUTHORIZE SCOPE)
	 */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
