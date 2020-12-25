package com.ruoyi.fee.domain;

/**
 * Layui表格封装json格式数据工具类
 */
public class ServerLayResult<T> {
    //返回码，用来判断访问是否正确
    private int code;
    //返回信息
    private String msg;
    //记录数
    private long count;
    //返回数据
    private  T data;

    public ServerLayResult() {
    }

    public ServerLayResult(int code, String msg, long count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
