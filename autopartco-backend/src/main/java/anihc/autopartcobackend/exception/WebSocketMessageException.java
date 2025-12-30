package anihc.autopartcobackend.exception;

public class WebSocketMessageException extends RuntimeException {
    public WebSocketMessageException(String message) {
        super(message);
    }

    public WebSocketMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
