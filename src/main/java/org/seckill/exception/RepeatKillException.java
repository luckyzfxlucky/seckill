package org.seckill.exception;

/**
 * Created by zfx on 2016/8/31.
 * 重复秒杀异常（运行期异常）
 * java异常有编译期和运行期异常：运行期异常不用我们手动的try...catch...
 * 还有spring的声明事务只接收运行期异常回滚
 */
public class RepeatKillException extends  RuntimeException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
