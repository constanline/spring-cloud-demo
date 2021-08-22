package com.magicalyang.springcloud.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Constanline
 * @since 2021/08/04
 */
@ApiModel(description = "登录信息")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignInDTO {
    @ApiModelProperty(value = "用户名", example = "magical")
    String username;

    @ApiModelProperty(value = "密码", example = "123456")
    String password;
}
