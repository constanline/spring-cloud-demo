package com.magicalyang.springcloud.account.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author  Constanline
 * @since   2021/08/02
 */
@Data
public class BizBookListDetail implements Serializable {
    private Integer id;

    private Integer bookListId;

    private Integer bookId;

    private String bookName;

    private static final long serialVersionUID = 1L;
}