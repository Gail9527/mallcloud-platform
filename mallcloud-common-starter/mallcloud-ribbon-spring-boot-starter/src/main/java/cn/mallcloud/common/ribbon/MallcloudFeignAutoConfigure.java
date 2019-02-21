package cn.mallcloud.common.ribbon;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign统一配置
 *
 * @author zscat
 * @date 2018/9/18 14:04
 */
@Configuration
public class MallcloudFeignAutoConfigure {

    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public XlabelFeignHeaderInterceptor xlabelFeignHeaderInterceptor() {
        return new XlabelFeignHeaderInterceptor();
    }
}
