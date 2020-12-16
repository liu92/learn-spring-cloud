package com.learn.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CommonResult
 * @Description:
 * @Author: lin
 * @Date: 2020/8/15 22:04
 * History:
 * @<version> 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;


    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
