package cn.mallcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * zuul gateway
 *
 * @author zscat
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MallcloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallcloudGatewayApplication.class, args);
	}
}
