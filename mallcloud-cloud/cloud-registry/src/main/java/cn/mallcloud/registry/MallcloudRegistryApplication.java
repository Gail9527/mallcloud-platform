package cn.mallcloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 * @author zscat
 */
@EnableEurekaServer
@SpringBootApplication
public class MallcloudRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallcloudRegistryApplication.class, args);
	}
}
