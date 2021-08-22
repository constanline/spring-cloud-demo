package com.magicalyang.springcloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

/**
 * @author Constanline
 * @since 2021/07/28
 */

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfigure {
    public static WebSessionServerSecurityContextRepository securityContextRepository =
            new WebSessionServerSecurityContextRepository();

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/account/session").permitAll()
                .anyExchange().authenticated()
//                .and().formLogin()
                .and().httpBasic().securityContextRepository(securityContextRepository)
                .and().csrf().disable().build();
    }
}
