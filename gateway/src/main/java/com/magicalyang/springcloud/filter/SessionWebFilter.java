package com.magicalyang.springcloud.filter;

import com.magicalyang.springcloud.configuration.WebSecurityConfigure;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Constanline
 * @since 2021/08/04
 */
@Component
public class SessionWebFilter implements WebFilter, Ordered {

    private Mono<Void> auth(String userId, ServerWebExchange exchange) {
        SecurityContextImpl securityContext = new SecurityContextImpl();

        UserDetails userDetails = new User(userId, "", new ArrayList<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", new ArrayList<>());

        securityContext.setAuthentication(authentication);
        return WebSecurityConfigure.securityContextRepository.save(exchange, securityContext)
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }

    public Mono<Void> setCookie(ServerWebExchange exchange, WebFilterChain chain) {
        return exchange.getSession().flatMap(webSession -> {
            ResponseCookie cookie = ResponseCookie.fromClientResponse("SESSION", webSession.getId())
                    .path("/").httpOnly(true).sameSite("Lax").build();
            exchange.getResponse().addCookie(cookie);
            return chain.filter(exchange);
        });
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        val request = exchange.getRequest();
        String path = request.getPath().toString();
        if (Pattern.matches("/account/session", path)) {
            if (request.getMethod() == HttpMethod.DELETE) {
                return WebSecurityConfigure.securityContextRepository.save(exchange, null);
            } else if (request.getMethod() == HttpMethod.POST) {
                ServerHttpResponse response = exchange.getResponse();
                response.beforeCommit(() -> {
                    HttpHeaders headers = response.getHeaders();
                    List<String> list = headers.get("userId");
                    if (list != null) {
                        String userId = list.get(0);
                        headers.remove("userId");
                        return auth(userId, exchange).then(setCookie(exchange, chain));
                    }
                    return Mono.empty();
                });
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
