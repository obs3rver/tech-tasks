package pl.techgarden.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import pl.techgarden.tasks.payu.PayuCreateOrderUseCase;

@SpringBootApplication
@EnableFeignClients
@EnableOAuth2Client
public class TasksApplication {

    @Autowired
    private PayuCreateOrderUseCase createOrderUseCase;

    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }
}
