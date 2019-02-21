package cn.MallcloudRevereseZuulProxyConfiguration.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 *
 * @author zscat
 * @date 2017/11/15 16:55
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class MallcloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallcloudConfigApplication.class, args);
    }
}
