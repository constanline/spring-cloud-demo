package com.magicalyang.springcloud.account.enums;

import com.magicalyang.springcloud.common.enums.BaseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum of Register error.
 *
 * @author Constanline
 * @since 2021/07/30
 */
@Getter
@AllArgsConstructor
public enum UserRegisterCodeEnum implements BaseCodeEnum {
    /**
     * Register success user register code enum.
     */
    REGISTER_SUCCESS(0, "注册成功"),
    /**
     * Not allowed character register error enum.
     */
    NOT_ALLOWED_CHARACTER(10011, "用户名不能包含特殊字符"),
    /**
     * Username exist register error enum.
     */
    USERNAME_EXIST(10012, "用户名已被占用"),
    /**
     * Username out of range register error enum.
     */
    USERNAME_OUT_OF_RANGE(10013, "用户名长度超出范围"),
    /**
     * Password out of range register error enum.
     */
    PASSWORD_OUT_OF_RANGE(10014, "密码长度超出范围"),
    /**
     * Register failure user register code enum.
     */
    REGISTER_FAILURE(10019, "注册失败");

    private final Integer code;
    private final String msg;
}
