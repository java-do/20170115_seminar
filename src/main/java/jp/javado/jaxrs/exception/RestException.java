package jp.javado.jaxrs.exception;

/**
 * Created by haruki on 2016/12/18.
 */
public class RestException extends Exception {

    private ErrorCase errorCase;

    public RestException(ErrorCase errorCase) {
        super(errorCase.getErrorCaseMessage());
        this.errorCase = errorCase;
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RestException(Throwable throwable) {
        super(throwable);
    }

    public ErrorCase getErrorCaseMessage() { return errorCase; }
}
