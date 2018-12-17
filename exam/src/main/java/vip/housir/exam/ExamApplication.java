package vip.housir.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author housirvip
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"vip.housir.exam", "vip.housir.base"})
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
}
