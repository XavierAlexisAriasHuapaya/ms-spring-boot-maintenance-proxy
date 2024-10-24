package dev.arias.huapaya.ms_maintenance_proxy.configuration.bean;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class CircuitBreakerBean {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomCB() {

        var circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .failureRateThreshold(60) // Se abrira si el 60% de las llamadas fallan
                .waitDurationInOpenState(Duration.ofSeconds(90)) // Permanece abierto 90 segundos antes de volver a
                                                                 // probar
                .slidingWindowSize(2) // Si 2 llamadas consecutivas fallan, circuit breaker abrira
                .build();

        var timeLimiterConfig = TimeLimiterConfig
                .custom()
                .timeoutDuration(Duration.ofSeconds(5)) // Si dura mas de 5 segundos se considera fallo por timeout
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build());

    }

}
