package com.example.crawler.vo;

import lombok.Data;

import org.springframework.http.HttpStatus;

/**
 * @author kpq
 * @since 1.0.0
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;


    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVo = new ResultVO<>();
        resultVo.setCode(HttpStatus.OK.value());
        resultVo.setMsg(HttpStatus.OK.getReasonPhrase());
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> ResultVO<T> error(Integer code, String msg) {
        ResultVO<T> resultVo = new ResultVO<>();
        resultVo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultVo.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return resultVo;
    }
}

