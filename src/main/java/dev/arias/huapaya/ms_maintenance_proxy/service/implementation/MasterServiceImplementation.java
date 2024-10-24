package dev.arias.huapaya.ms_maintenance_proxy.service.implementation;

import java.util.Map;
import java.util.HashMap;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.arias.huapaya.ms_maintenance_proxy.models.Master;
import dev.arias.huapaya.ms_maintenance_proxy.persistence.repository.FallbackRepository;
import dev.arias.huapaya.ms_maintenance_proxy.persistence.repository.MasterRepository;
import dev.arias.huapaya.ms_maintenance_proxy.service.interfaces.MasterService;
import dev.arias.huapaya.ms_maintenance_proxy.streams.MasterPublisher;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MasterServiceImplementation implements MasterService {

    private final MasterRepository masterRepository;

    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    private final MasterPublisher masterPublisher;

    private final ObjectMapper objectMapper;

    private final FallbackRepository fallbackRepository;

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> findAll() {
        var circuitBreaker = this.circuitBreakerFactory.create("master-circuitbreaker");
        return circuitBreaker.run(() -> this.masterRepository.findAll(),
                throwable -> this.fallbackRepository.getFallback());
    }

    @Transactional(readOnly = false)
    @Override
    public Map<String, Object> save(Master master) {
        var circuitBreaker = this.circuitBreakerFactory.create("master-circuitbreaker-event");
        this.masterPublisher.publisher(this.buildEventMsg(master));
        circuitBreaker.run(() -> this.masterRepository.save(master),
                throwable -> this.masterPublisher.publisherCb(this.buildEventMsg(master)));
        Map<String, Object> response = new HashMap<>();
        response.put("data", master);
        return response;
    }

    private String buildEventMsg(Master master) {
        try {
            return this.objectMapper.writeValueAsString(master);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
