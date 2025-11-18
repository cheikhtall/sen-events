package sn.dev.ct.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "sn.dev.ct")
@EnableJpaRepositories(basePackages = "sn.dev.ct.core.domain")
@EntityScan(basePackages = "sn.dev.ct.core.domain")
public class SenEventsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SenEventsApplication.class, args);
    }
}
