package dev.arias.huapaya.ms_maintenance_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MsMaintenanceProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMaintenanceProxyApplication.class, args);
	}

}
