package cn.mallcloud.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * mallcloud oauth2 配置类
 *
 * @author zscat
 * 2018/12/4 15:34
 */
@ConfigurationProperties("mallcloud.oauth2")
@Data
public class MallcloudOauth2Properties {

    /**
     * accessTokenValiditySeconds, default: 30 days.
     */
    private int accessTokenValiditySeconds = 60 * 60 * 24 * 30;

    /**
     * refreshTokenValiditySeconds, default: 12 hours.
     */
    private int refreshTokenValiditySeconds = 60 * 60 * 12;

    /**
     * the urls for permitAll.
     */
    private List<String> urlPermitAll = new ArrayList<>();

    /**
     * The key store properties for locating a key in a Java Key Store (a file in a format
     * defined and understood by the JVM).
     */
    private KeyStore keyStore = new KeyStore();

    @Data
    @ConfigurationProperties("encrypt")
    public static class KeyStore {

        /**
         * Location of the key store file, e.g. classpath:/keystore.jks.
         */
        private Resource location;

        /**
         * Password that locks the keystore.
         */
        private String password;

        /**
         * Alias for a key in the store.
         */
        private String alias;

        /**
         * Secret protecting the key (defaults to the same as the password).
         */
        private String secret;

    }
}
