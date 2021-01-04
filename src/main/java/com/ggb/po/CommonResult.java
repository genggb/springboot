package com.ggb.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述:基于RestFul风格的返回类型
 * @Author: genggb
 * @Date: 2020-1-20 17:11
 */
@Getter
@Setter
public class CommonResult {

    private String status;

    private Object result;

    private Object message;
}
