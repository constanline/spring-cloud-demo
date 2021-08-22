package com.magicalyang.springcloud.account;

import com.magicalyang.springcloud.account.controller.SessionController;
import com.magicalyang.springcloud.account.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Constanline
 * @since 2021/07/25
 */
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
