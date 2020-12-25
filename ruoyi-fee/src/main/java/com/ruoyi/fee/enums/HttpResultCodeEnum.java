package com.ruoyi.fee.enums;

import lombok.AllArgsConstructor;

/**
 * @author jiangzw
 * @Title: HttpResultCodeEnum
 * @ProjectName xtjc_reservation
 * @Description: //TODO 前台调用接口的返回code，用来判断返回信息的
 * @Company: China united network communication co. LTD
 * @date 2020-2-23 13:23
 */

@AllArgsConstructor
public enum HttpResultCodeEnum implements EnumValue{


    MAX_COUNT(false,1,"当天取消次数超过三次，不能再预约"),
    MAX_SIZE(false,2,"当前时间段预约已满"),
    INVALID_PARAM(false,10003,"非法参数！"),
    SUCCESS(true,0,"操作成功！"),
    FAIL(false,-1,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
//    MAX_COUNT(false,1,"当天取消次数已经达到最大上限");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
    public boolean getSuccess(){
        return success;
    }
    @Override
    public Object toValue() {
        return message;
    }
}
