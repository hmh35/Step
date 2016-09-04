package cn.edu.fjnu.exception;

/**
 * @Author: linqiu
 * @Date: 2016/3/4 10:43
 * @Description: 系统异常
 */
public class AppRTException extends RuntimeException {

    private Object[] params;
    private String code;
    private String msg;
    private long timestamp;

    public AppRTException(String code, String msg) {
        super(msg);
        this.code = code;
        timestamp = System.currentTimeMillis();
    }

    /*public AppRTException(String code, String msg, Throwable e) {
        super(msg,e);

    }*/

    public AppRTException(String code, Object[] params, String msg) {
        this(code, msg);
        this.params = params;
    }

    /*public AppRTException(String code, Object[] params, String msg, Throwable e) {
        this(code, msg, e);
        this.params = params;
    }*/

    public Object[] getParams() {
        return params;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }
}
