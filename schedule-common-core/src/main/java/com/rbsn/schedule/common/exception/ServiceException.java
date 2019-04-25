package com.rbsn.schedule.common.exception;

/**
 * 服务层异常类
 */
public class ServiceException extends Exception {

    /**
     * 错误data
     */
    private Object errorData;

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object errorData) {
        super(message);
        this.errorData = errorData;
    }

    public ServiceException(Throwable cause, Object errorData) {
        super(cause);
        this.errorData = errorData;
    }

    /**
     * controller层获取服务异常信息
     *
     * @return
     */
    @Override
    public String getMessage() {
        Throwable cause = super.getCause();
        if (cause == null) {
            return super.getMessage();
        } else {
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            return cause.getMessage();
        }
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }
}
