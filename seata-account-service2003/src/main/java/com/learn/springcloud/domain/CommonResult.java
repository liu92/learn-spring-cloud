package com.learn.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公用返回结果
 * @ClassName: CommonResult
 * @Description:
 * @Author: lin
 * @Date: 2020/8/26 15:19
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


    public CommonResult(Integer code, String message){
        this(code, message, null);
    }



}
