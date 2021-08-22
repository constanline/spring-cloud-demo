package com.magicalyang.springcloud.administrator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Constanline
 * @since 2021/07/27
 */
@RestController
@RequestMapping("/service")
public class TestSessionController {

    @RequestMapping("/test")
    public String test(HttpSession session) {
        return (String) session.getAttribute("id");
    }
}
