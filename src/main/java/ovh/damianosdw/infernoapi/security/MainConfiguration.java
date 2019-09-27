/*
 * Created by DamianosDW
 * https://damianosdw.ovh
 */

package ovh.damianosdw.infernoapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration
{
    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder userPasswordEncoder()
    {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public PasswordEncoder clientPasswordEncoder()
    {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public UserDetailsService userDetailsService(final PasswordEncoder userPasswordEncoder)
    {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user, password, active FROM users WHERE user = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user, role FROM users WHERE user = ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public FailureHandler failureHandler() {
        return new FailureHandler();
    }

    @Bean
    public SuccessHandler successHandler()
    {
        return new SuccessHandler();
    }

    @Bean
    public TokenStore tokenStore()
    {
        return new JdbcTokenStore(dataSource);
    }
}
