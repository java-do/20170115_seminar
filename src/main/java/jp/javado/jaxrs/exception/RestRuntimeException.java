package jp.javado.jaxrs.exception;

/**
 * Created by haruki on 2017/01/06.
 */
public class RestRuntimeException extends RuntimeException {

    private ErrorCase errorCase;

    public RestRuntimeException(ErrorCase errorCase) {
        super(errorCase.getErrorCaseMessage());
        this.errorCase = errorCase;
    }

    public RestRuntimeException(ErrorCase errorCase, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCase = errorCase;
    }

    public RestRuntimeException(String message) { super(message); }

    public RestRuntimeException(String message, Throwable throwable) { super(message, throwable); }

    public RestRuntimeException(Throwable throwable) { super(throwable); }

    public ErrorCase getErrorCase() { return this.errorCase; }

}
