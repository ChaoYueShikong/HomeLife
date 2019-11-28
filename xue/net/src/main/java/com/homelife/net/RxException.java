package com.homelife.net;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public class RxException extends Exception {
    private int mCode;
    private String mMsg;

    public RxException() {
        super();
    }

    public RxException(int code, String msg) {
        super(msg);
        mCode = code;
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    @Override
    public String toString() {
        return "RxException{" +
                "mCode=" + mCode +
                ", mMsg='" + mMsg + '\'' +
                '}';
    }
}
