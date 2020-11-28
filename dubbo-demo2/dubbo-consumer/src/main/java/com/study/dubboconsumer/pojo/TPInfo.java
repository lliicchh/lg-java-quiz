package com.study.dubboconsumer.pojo;

public class TPInfo {

    private Long consume;

    private String methodName;

    public TPInfo() {
    }

    public TPInfo(Long consume, String methodName) {
        this.consume = consume;
        this.methodName = methodName;
    }

    public Long getConsume() {
        return consume;
    }

    public void setConsume(Long consume) {
        this.consume = consume;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
