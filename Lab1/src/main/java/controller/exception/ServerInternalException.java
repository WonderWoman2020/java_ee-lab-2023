package controller.exception;

public class ServerInternalException extends HttpRequestException{

    private static final int RESPONSE_CODE = 500;
    public ServerInternalException() {
        super(RESPONSE_CODE);
    }

    public ServerInternalException(String message) {
        super(message, RESPONSE_CODE);
    }

    public ServerInternalException(String message, Throwable cause) {
        super(message, cause, RESPONSE_CODE);
    }

    public ServerInternalException(Throwable cause) {
        super(cause, RESPONSE_CODE);
    }

    public ServerInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace, RESPONSE_CODE);
    }
}
