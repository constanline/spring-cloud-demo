package com.magicalyang.springcloud.account.controller;

import com.magicalyang.springcloud.account.dto.RegisterDTO;
import com.magicalyang.springcloud.account.service.UserService;
import com.magicalyang.springcloud.common.response.MessageResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Constanline
 * @since 2021/07/28
 */
@EnableOpenApi
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public MessageResponse get(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public MessageResponse register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @DeleteMapping("/{userId}")
    public MessageResponse cancel(@PathVariable int userId) {
        return userService.cancel(userId);
    }

    @GetMapping
    public MessageResponse search(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String displayName,
            @RequestParam(defaultValue = "0") Long lastLoginTime) {
        return userService.search(username, displayName, lastLoginTime);
    }
}
