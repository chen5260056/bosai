package per.muyi.bosai.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponseModel<T> {
    /**
     * 错误信息
     */
    private String errorMessage;
}
