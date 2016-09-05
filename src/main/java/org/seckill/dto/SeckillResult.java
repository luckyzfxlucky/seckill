package org.seckill.dto;

/**
 * Created by zfx on 2016/9/1.
 * 所有Ajex请求返回类型，封闭json结果
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SeckillResult(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(T data) {
        this.data = data;
    }
}
