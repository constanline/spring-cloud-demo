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
public enum UserCommonCodeEnum implements BaseCodeEnum {
    /**
     * Operation success user common code enum.
     */
    OPERATION_SUCCESS(0, "操作成功"),
    /**
     * Sign in success user common code enum.
     */
    SIGN_IN_SUCCESS(0, "登录成功"),
    /**
     * Username not exist user common code enum.
     */
    USERNAME_NOT_EXIST(10001, "用户名不存在"),
    /**
     * User not exist user common code enum.
     */
    USER_NOT_EXIST(10002, "用户不存在"),
    /**
     * Username or password error user common code enum.
     */
    USERNAME_OR_PASSWORD_ERROR(10003, "用户名或密码错误"),
    /**
     * Sign in failure user common code enum.
     */
    SIGN_IN_FAILURE(10004, "登录失败");

    private final Integer code;
    private final String msg;
}
