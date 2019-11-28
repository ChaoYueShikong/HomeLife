package com.homelife.xhs.bean;

public class LoginResponse {


    /**
     * code : 0
     * data : {"token":"b82837ea-05ce-41ed-a4a6-885d48760d40"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : b82837ea-05ce-41ed-a4a6-885d48760d40
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
