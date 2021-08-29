package com.example.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.oauth2Client();
//    http.authorizeRequests().anyRequest().permitAll();
//  }
  
  @Bean
  public ClientRegistrationRepository clientRepository() {
      var c = clientRegistration();
      return new InMemoryClientRegistrationRepository(c);
  }
  
	private ClientRegistration clientRegistration() {
		ClientRegistration cr = ClientRegistration
				.withRegistrationId("app")
				.clientId("rs1")
				.clientSecret("rssecret1")
				.scope(new String[] { "read", "write" })
				//.authorizationUri("https://github.com/login/oauth/authorize")
				.tokenUri("http://localhost:6060/oauth/token")
				//.userInfoUri("https://api.github.com/user")
				.userNameAttributeName("id")
				.clientName("app")
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				//.redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
				.build();
		return cr;
	}


  @Bean
  public OAuth2AuthorizedClientManager authorizedClientManager(
          ClientRegistrationRepository clientRegistrationRepository,
          OAuth2AuthorizedClientRepository authorizedClientRepository) {

    OAuth2AuthorizedClientProvider authorizedClientProvider =
            OAuth2AuthorizedClientProviderBuilder.builder()
                    .clientCredentials()
                    .build();

    var authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                                          clientRegistrationRepository,
                                          authorizedClientRepository);

    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

    return authorizedClientManager;
  }
  
  @Bean
  WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
      ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
              new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
      return WebClient.builder()
              .apply(oauth2Client.oauth2Configuration())
              .build();
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
  	 web.ignoring().antMatchers(
               "/test/**");
  }
  
}
