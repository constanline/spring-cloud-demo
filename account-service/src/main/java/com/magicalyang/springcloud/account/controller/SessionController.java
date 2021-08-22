package com.magicalyang.springcloud.account.controller;

import com.magicalyang.springcloud.account.dto.SignInDTO;
import com.magicalyang.springcloud.account.service.UserService;
import com.magicalyang.springcloud.account.vo.UserVO;
import com.magicalyang.springcloud.common.response.MessageResponse;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Constanline
 * @since 2021/07/27
 */
@Api(tags = "用户登入登出" )
@RestController
@RequestMapping("/session")
public class SessionController {

    UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登入" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "signInDTO", value = "账号登录信息", dataTypeClass = SignInDTO.class)
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = UserVO.class)})
    @PostMapping
    public MessageResponse signIn(@RequestBody SignInDTO signInDTO, HttpServletResponse response) {
        UserVO userVO = new UserVO();
        val messageResponse = userService.signIn(signInDTO, userVO);
        if (messageResponse.getErrCode().equals(0)) {
            response.setHeader("userId", userVO.getId().toString());
        }
        return messageResponse;
    }

    @ApiOperation(value = "用户登出" , httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", dataTypeClass = Integer.class)
    })
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @DeleteMapping("/{userId}")
    public MessageResponse signOut(@PathVariable Integer userId) {
        return userService.signOut(userId);
    }
}
