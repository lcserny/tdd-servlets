package net.cserny.tdd.servlet.configuration;

import net.cserny.tdd.servlet.FakeAuthenticationService;
import net.cserny.tdd.servlet.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {
    @Bean
    public AuthenticationService authenticationService() {
        return new FakeAuthenticationService();
    }
}
