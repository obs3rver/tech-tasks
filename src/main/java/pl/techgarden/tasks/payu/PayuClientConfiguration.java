package pl.techgarden.tasks.payu;

import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
class PayuClientConfiguration {

    @Value("${security.oauth2.payu.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.payu.client-id}")
    private String clientId;

    @Value("${security.oauth2.payu.client-secret}")
    private String clientSecret;

    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(accessTokenUri);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        return details;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    Decoder responseEntityDecoder() {
        return new ResponseEntityDecoder(new GsonDecoder());
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new FeignRedirectionErrorDecoder();
    }

    @Bean
    Client okhttpClient() {
        return new feign.okhttp.OkHttpClient(
                new OkHttpClient().newBuilder()
                        .followRedirects(false)
                        .followSslRedirects(false)
                        .build()
        );
    }

}
