package com.common.system;

/**
 * 自定义异常类
 * 用来抛出程序执行时自定义异常信息
 *
 * @author hj
 */
public class SysRuntimeException extends RuntimeException {
    public SysRuntimeException(String msg) {
        super(msg);
    }
}
