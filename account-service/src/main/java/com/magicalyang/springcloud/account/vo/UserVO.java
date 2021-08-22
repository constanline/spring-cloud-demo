package com.magicalyang.springcloud.account.vo;

import com.magicalyang.springcloud.common.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 * @since 2021/07/23
 */
@ApiModel(description = "用户信息")
@Getter
@Setter
@ToString
public class UserVO implements BaseVO {

    @ApiModelProperty(value = "用户ID", example = "1")
    Integer id;

    @ApiModelProperty(value = "用户名", example = "magical")
    String username;

    @ApiModelProperty(value = "显示名称", example = "魔术师")
    String displayName;
}
