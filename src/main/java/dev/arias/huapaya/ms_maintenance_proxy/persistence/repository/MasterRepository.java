package dev.arias.huapaya.ms_maintenance_proxy.persistence.repository;

import java.util.Map;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.arias.huapaya.ms_maintenance_proxy.configuration.bean.LoadBalancerConfiguration;
import dev.arias.huapaya.ms_maintenance_proxy.models.Master;

@FeignClient(name = "ms-maintenance")
@LoadBalancerClient(name = "ms-maintenance", configuration = LoadBalancerConfiguration.class)
public interface MasterRepository {

    @GetMapping(path = "/api/maintenance/master")
    public Map<String, Object> findAll();

    @PostMapping(path = "/api/maintenance/master")
    public Map<String, Object> save(@RequestBody Master master);

}
