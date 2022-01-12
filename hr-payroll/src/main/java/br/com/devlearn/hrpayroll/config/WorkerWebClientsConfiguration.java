package br.com.devlearn.hrpayroll.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class WorkerWebClientsConfiguration {
    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("hr-worker");
    }

    class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {
        private final String serviceId;

        public DemoServiceInstanceListSuppler(String s) {
            this.serviceId = s;
        }

        @Override
        public String getServiceId() {
            return serviceId;
        }

        @Override
        public Flux<List<ServiceInstance>> get() {
            return Flux.just(
                Arrays.asList(
                    new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8001, false),
                    new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8002, false)
                )
            );
        }
    }
}
