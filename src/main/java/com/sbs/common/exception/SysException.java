package com.sbs.common.exception;

/**
 * 系统内部异常
 *
 */
public class SysException extends RuntimeException  {

    private static final long serialVersionUID = -994962710559017255L;

    public SysException(String message) {
        super(message);
    }
}
