package org.seckill.exception;

/**
 * Created by zfx on 2016/8/31.
 * 秒杀关闭异常
 */
public class SeckillCloseException extends RuntimeException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
