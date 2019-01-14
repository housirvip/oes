package vip.housir.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author housirvip
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"vip.housir.support", "vip.housir.base"})
public class SupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportApplication.class, args);
    }
}
