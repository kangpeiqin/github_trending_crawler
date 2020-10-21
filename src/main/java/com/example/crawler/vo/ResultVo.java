package com.example.crawler.vo;

import lombok.Data;

import org.springframework.http.HttpStatus;

/**
 * @author kpq
 * @since 1.0.0
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;


    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(HttpStatus.OK.value());
        resultVo.setMsg(HttpStatus.OK.getReasonPhrase());
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> ResultVo<T> error(Integer code, String msg) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultVo.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return resultVo;
    }
}

