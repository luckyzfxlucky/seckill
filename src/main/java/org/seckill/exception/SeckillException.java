package org.seckill.exception;

/**
 * Created by zfx on 2016/8/31.
 * 秒杀异常，SeckillCloseException和RepeatKillException是SeckillException的子类
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
