package com.woniuxy.cq.ssmboot39.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /*
     *  成功
     */
    private boolean success;

    /**
     * 结果码
     * 0000 成功
     * 1000 业务异常
     * 1001 用户不存在
     * 2000 系统异常
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果数据
     */
    private Object data;

    public static Result success(){
        return success(null);
    }

    public static  Result success(Object data){
        return new  Result(true,"0000","执行成功",data);
    }

    public static  Result fail(String code, String message){
        return new  Result(false,code,message,null);
    }

}
