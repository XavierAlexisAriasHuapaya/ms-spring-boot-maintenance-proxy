package dev.arias.huapaya.ms_maintenance_proxy.configuration.bean;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {

    public ServiceInstanceListSupplier instanceListSuppliers(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient() // Se utiliza eureka como fuente externa porque conoce las instancias
                                               // disponibles
                .build(context);
    }

}
