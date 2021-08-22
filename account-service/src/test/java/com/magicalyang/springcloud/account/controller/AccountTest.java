package com.magicalyang.springcloud.account.controller;

import com.magicalyang.springcloud.account.dto.RegisterDTO;
import com.magicalyang.springcloud.account.dto.SignInDTO;
import com.magicalyang.springcloud.account.vo.UserVO;
import com.magicalyang.springcloud.common.response.DataResponse;
import com.magicalyang.springcloud.common.util.BeanUtil;
import com.magicalyang.springcloud.common.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Constanline
 * @since 2021/08/04
 */
@SpringBootTest
class AccountTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void allInOne() throws Exception {
        MockHttpServletResponse response;
        String data;
        String resp;

        // 注册
        RegisterDTO registerDTO = new RegisterDTO("test", "123456", "测试");
        data = JsonUtil.toJson(registerDTO);
        System.out.println("Req:" + data);
        response = mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andReturn().getResponse();
        System.out.println("Resp:" + response.getContentAsString(StandardCharsets.UTF_8));

        // 搜索
        response = mockMvc.perform(get("/user")).andReturn().getResponse();
        resp = response.getContentAsString(StandardCharsets.UTF_8);
        System.out.println("Resp:" + resp);

        // 登录
        SignInDTO signInDTO = new SignInDTO("test", "123456");
        data = JsonUtil.toJson(signInDTO);
        System.out.println("Req:" + data);
        response = mockMvc.perform(
                post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andReturn().getResponse();
        resp = response.getContentAsString(StandardCharsets.UTF_8);
        System.out.println("Resp:" + resp);
        DataResponse<?> dataResponse = JsonUtil.toModel(resp, DataResponse.class);
        UserVO userVO = BeanUtil.copyProperties(dataResponse.getData(), UserVO::new);

        // 登出
        response = mockMvc.perform(delete("/session/" + userVO.getId())).andReturn().getResponse();
        resp = response.getContentAsString(StandardCharsets.UTF_8);
        System.out.println("Resp:" + resp);

        // 注销
        response = mockMvc.perform(delete("/user/" + userVO.getId())).andReturn().getResponse();
        resp = response.getContentAsString(StandardCharsets.UTF_8);
        System.out.println("Resp:" + resp);
    }
}