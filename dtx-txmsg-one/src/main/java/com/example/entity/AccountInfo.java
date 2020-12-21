package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName learn-java-interview
 * @Description 描述
 * @Date 2020/12/21 9:53 上午
 * @Author lin
 * @Version 1.0
 */
@Data
public class AccountInfo  implements Serializable {
    private static final long serialVersionUID = 5709363085381478324L;
    private Long id;
    private String accountName;
    private String accountNo;
    private String accountPassword;
    private Double accountBalance;
}
