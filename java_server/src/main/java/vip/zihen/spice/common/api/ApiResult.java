package vip.zihen.spice.common.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应数据
     */
    private T data;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public ApiResult() {
        time  = new Date();
    }

    private static <E> ApiResult<E> result(ApiCode code, String message, E data ) {

        if (StringUtils.isBlank(message)) {
            message = code.getMessage();
        }

        return (ApiResult<E>) ApiResult.builder()
                .code(code.getCode())
                .message(message)
                .success(code.getCode() == ApiCode.SUCCESS.getCode())
                .time(new Date())
                .data(data)
                .build();
    }

    public static <E> ApiResult<E> ok(E data) {
        return result(ApiCode.SUCCESS, null, data);
    }

    public static <E> ApiResult<E> ok(E data, String message) {
        return result(ApiCode.SUCCESS, message, data);
    }

    public static ApiResult fail(ApiCode code) {
        return result(code, null, null);
    }

    public static ApiResult fail(String message) {
        return result(ApiCode.FAIL, message, null);
    }
}
