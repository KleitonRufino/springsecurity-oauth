package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	/*
	 * GET http://localhost:9090/test BEARER TOKEN (REQUEST RETURN FORBIDDEN - PRE AUTHORIZE SCOPE)
	 * GET http://localhost:9090/test2 BEARER TOKEN
	 */
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
