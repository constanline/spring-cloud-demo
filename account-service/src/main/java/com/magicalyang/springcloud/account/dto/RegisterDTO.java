package com.magicalyang.springcloud.account.dto;

import lombok.*;

/**
 * @author Constanline
 * @since 2021/08/02
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    String username;

    String password;

    String displayName;
}
