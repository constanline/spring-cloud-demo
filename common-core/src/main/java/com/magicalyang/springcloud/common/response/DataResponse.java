package com.magicalyang.springcloud.common.response;

import com.magicalyang.springcloud.common.enums.BaseCodeEnum;
import com.magicalyang.springcloud.common.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Constanline
 * @since 2021/07/28
 */
@ApiModel(description = "数据反馈")
@Getter
@Setter
@ToString
public class DataResponse<T> extends MessageResponse {
    @ApiModelProperty(value = "数据")
    T data;

    public DataResponse() {
        super();
    }

    public DataResponse(T data) {
        super(0, "");
        this.data = data;
    }

    public DataResponse(BaseCodeEnum codeEnum, T data) {
        super(codeEnum);
        this.data = data;
    }
}
