package com.example.client.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.client.managers.TokenManager;

@Component
public class ResourceServerProxy {

	public static final String AUTHORIZATION = "Authorization";

	@Value("${resource1.server.url}")
	private String resourceServerURL;

	@Value("${resource2.server.url}")
	private String resourceServerURL2;

	private final TokenManager tokenManager;

	private final RestTemplate restTemplate;

	
	public ResourceServerProxy(TokenManager tokenManager, RestTemplate restTemplate) {
		this.tokenManager = tokenManager;
		this.restTemplate = restTemplate;
	}

	public String callDemo() {
		String token = tokenManager.getAccessToken();

		String url = resourceServerURL + "/demo";

		HttpHeaders headers = new HttpHeaders();
		System.out.println("TOKEN " + token);
		headers.add(AUTHORIZATION, "Bearer " + token);
		HttpEntity<Void> request = new HttpEntity<>(headers);

		try{
			var response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			return response.getBody();
		}catch (Exception e) {
			return e.getMessage();
		}
	}

	public String callDemo2() {
		String token = tokenManager.getAccessToken();

		String url = resourceServerURL2 + "/demo";

		HttpHeaders headers = new HttpHeaders();
		System.out.println("TOKEN " + token);
		headers.add(AUTHORIZATION, "Bearer " + token);
		HttpEntity<Void> request = new HttpEntity<>(headers);

		try {
			var response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			return response.getBody();
			// String body = webClient.get().uri(url).header(AUTHORIZATION, "Bearer " +
			// token).retrieve().bodyToMono(String.class).block();
			// System.out.println("BODY " + body);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
