package vip.housir.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author housirvip
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"vip.housir.user", "vip.housir.base"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
