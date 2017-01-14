package jp.javado.jaxrs.pojo;

/**
 * Created by haruki on 2017/01/09.
 */
public class ErrorMessage {

    private String code;

    private String message;

    public ErrorMessage() {}

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() { return message; }

    public String getCode() { return code; }

    public void setMessage(String message) { this.message = message; }

    public void setCode(String code) { this.code = code; }

}
