package com.superb.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Superb
 * @date 2020/12/26 - 16:56
 * @E_mail superb12580@163.com
 */
@Data
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return new Result(200,"操作成功",data);
    }
    public static Result success(String msg, Object data){
        return new Result(200,msg,data);
    }
    public static Result fail(String msg){
        return new Result(400,msg,null);
    }
}
