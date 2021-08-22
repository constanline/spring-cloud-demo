package com.magicalyang.springcloud.common.response;

import com.magicalyang.springcloud.common.enums.BaseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Constanline
 * @since 2021/07/28
 */
@ApiModel(description = "消息反馈")
@Getter
@Setter
@ToString
public class MessageResponse implements Serializable {

    @ApiModelProperty(value = "错误代码", example = "0")
    Integer errCode;

    @ApiModelProperty(value = "错误消息", example = "成功")
    String errMsg;

    public MessageResponse() {
        this(0, "");
    }

    public MessageResponse(BaseCodeEnum codeEnum) {
        this.errCode = codeEnum.getCode();
        this.errMsg = codeEnum.getMsg();
    }

    public MessageResponse(String errMsg) {
        this.errCode = -1;
        this.errMsg = errMsg;
    }

    public MessageResponse(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
