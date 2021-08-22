package com.magicalyang.springcloud.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Constanline
 * @since 2021/08/06
 */
@Component
public class RestAuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {
    private final RedisTemplate<String, String> redisTemplate;

    public RestAuthenticationSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication){
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        httpHeaders.add(HttpHeaders.AUTHORIZATION, uuid);

        redisTemplate.boundValueOps(uuid).set(authentication.getName(), 2*60*60, TimeUnit.SECONDS);

        return webFilterExchange.getChain().filter(webFilterExchange.getExchange());
    }

}
