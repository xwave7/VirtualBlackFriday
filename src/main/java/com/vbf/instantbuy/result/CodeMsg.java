package com.vbf.instantbuy.result;

/**
 * Created by BRODY on 2019/12/2.
 */
public class CodeMsg {
    private int code;
    private String msg;

    // general status
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "ServerError");

    // login module 5002xx
    // item module 5003xx
    // order module 5004xx


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
