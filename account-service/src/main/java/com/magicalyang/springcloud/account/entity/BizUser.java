package com.magicalyang.springcloud.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author  Constanline
 * @since   2021/08/02
 */
@Data
public class BizUser implements Serializable {
    /**
    * id
    */
    private Integer id;

    /**
    * username
    */
    private String username;

    /**
    * password
    */
    private String password;

    /**
    * halt
    */
    private String halt;

    /**
    * displayName
    */
    private String displayName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime lastLoginTime;

    private static final long serialVersionUID = 1L;
}