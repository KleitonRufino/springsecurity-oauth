package com.example.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ResourceServerProxy {

	public static final String AUTHORIZATION = "Authorization";

	@Value("${resource.server.url}")
	private String resourceServerURL;

	private final WebClient webClient;
	private final TokenManager tokenManager;

	public ResourceServerProxy(TokenManager tokenManager, WebClient webClient) {
		this.tokenManager = tokenManager;
		this.webClient = webClient;
	}

	public String callDemo() {
		String token = tokenManager.getAccessToken();
		System.out.println("TOKEN " + token);
		String url = resourceServerURL + "/demo";

		try {
			String body = webClient.get().uri(url).header(AUTHORIZATION, "Bearer " + token).retrieve().bodyToMono(String.class).block();
			return body;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
