package cn.mallcloud.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * auth2 启动类
 *
 * @author zscat
 * @date 2018/7/23 10:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MallcloudOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallcloudOauth2ServerApplication.class, args);
    }
}
