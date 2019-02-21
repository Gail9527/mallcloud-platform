package cn.mallcloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * mallcloud 服务治理service
 *
 * @author zscat
 * @date 2017/11/18 20:24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulServer
public class MallcloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallcloudAdminApplication.class, args);
    }
}
