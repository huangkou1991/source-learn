package com.learn.bean;

/**
 * @Author :lwy
 * @Date : 2018/11/21 19:07
 * @Description :
 */
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static final class Builder<T> {
        private Integer code;
        private String message;
        private T data;

        private Builder() {
        }

        public static Builder aResult() {
            return new Builder();
        }

        public Builder withCode(Integer code) {
            this.code = code;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withData(T data) {
            this.data = data;
            return this;
        }

        public Result build() {
            Result result = new Result();
            result.setCode(code);
            result.setMessage(message);
            result.setData(data);
            return result;
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
