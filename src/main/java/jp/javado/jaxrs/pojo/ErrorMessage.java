package jp.javado.jaxrs.pojo;

/**
 * Created by haruki on 2017/01/09.
 */
public class ErrorMessage {

    private String message;

    public ErrorMessage() {}

    public ErrorMessage(String message) { this.message = message; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
