/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import ovh.damianosdw.infernoapi.utils.ApiUtils;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter
{
    private final PasswordEncoder clientPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private static final int ACCESS_TOKEN_VALIDITY_IN_SECONDS = 10800;
    private static final int REFRESH_TOKEN_VALIDITY_IN_SECONDS = 30*24*3600;

    @Autowired
    DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler()
    {
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
    {
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(clientPasswordEncoder)
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.inMemory()
                .withClient(ApiUtils.CLIENT_ID)
                .secret(ApiUtils.CLIENT_SECRET)
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ADMIN", "USER")
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_IN_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_IN_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
                .tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
