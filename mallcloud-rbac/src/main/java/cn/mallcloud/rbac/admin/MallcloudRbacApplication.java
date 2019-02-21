package cn.mallcloud.rbac.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zscat
 * @date 2017年10月27日13:59:05
 */
@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
public class MallcloudRbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallcloudRbacApplication.class, args);
    }
}
