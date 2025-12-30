package anihc.autopartcobackend.exception;

public class WebSocketAuthenticationException extends RuntimeException {
    public WebSocketAuthenticationException(String message) {
        super(message);
    }

    public WebSocketAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
