package com.example.crawler.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @date 2021/10/27
 */
@Data
@Accessors(chain = true)
public class Result {

    private int statusCode;

    private Object data;

}
