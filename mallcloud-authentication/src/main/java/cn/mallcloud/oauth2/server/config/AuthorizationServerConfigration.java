package cn.mallcloud.oauth2.server.config;

import cn.mallcloud.common.config.MallcloudOauth2Properties;
import cn.mallcloud.common.constants.CommonConstant;
import cn.mallcloud.common.constants.SecurityConstants;
import cn.mallcloud.oauth2.server.userdetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务器配置抽象
 *
 * @author zscat
 * @date 2018/7/24 16:10
 */
@Configuration
@EnableConfigurationProperties({MallcloudOauth2Properties.class})
@EnableAuthorizationServer
public class AuthorizationServerConfigration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MallcloudOauth2Properties oauth2Properties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory
                (oauth2Properties.getKeyStore().getLocation(), oauth2Properties.getKeyStore().getSecret().toCharArray())
                .getKeyPair(oauth2Properties.getKeyStore().getAlias());
        converter.setKeyPair(keyPair);
        return converter;
    }

    /**
     * jwt 生成token 定制化处理
     *
     * 添加一些额外的用户信息到token里面
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("license", SecurityConstants.LICENSE);
            final Object principal = authentication.getUserAuthentication().getPrincipal();
            UserDetailsImpl user;
            if (principal instanceof UserDetailsImpl) {
                user = (UserDetailsImpl) principal;
            } else {
                final String username = (String) principal;
                user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
            }
            additionalInfo.put("userId", user.getUserId());
            additionalInfo.put(CommonConstant.HEADER_LABEL, user.getLabel());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        return clientDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 授权服务器端点配置，如令牌存储，令牌自定义，用户批准和授权类型，不包括端点安全配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setAccessTokenValiditySeconds(oauth2Properties.getAccessTokenValiditySeconds());
        defaultTokenServices.setRefreshTokenValiditySeconds(oauth2Properties.getRefreshTokenValiditySeconds());

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter(), tokenEnhancer()));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(defaultTokenServices);
    }

    /**
     * 授权服务器端点的安全配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                // 让/oauth/token支持client_id以及client_secret作登录认证
                .allowFormAuthenticationForClients();
    }
}
