package com.magicalyang.springcloud.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author  Constanline
 * @since   2021/08/02
 */
@Data
public class BizBookList implements Serializable {
    private Integer id;

    private Integer userId;

    private String listName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String description;

    private static final long serialVersionUID = 1L;
}