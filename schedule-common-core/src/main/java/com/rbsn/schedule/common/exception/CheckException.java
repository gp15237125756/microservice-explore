package com.rbsn.schedule.common.exception;

/**
 * 校验异常类
 */
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
