package anihc.autopartcobackend.exception;

import anihc.autopartcobackend.exception.model.ErrorResponse;
import anihc.autopartcobackend.exception.model.constant.ErrorCode;
import anihc.autopartcobackend.exception.model.constant.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    private String generateRequestId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.VALIDATION_ERROR,
                "Validation failed: " + String.join(", ", errors),
                generateRequestId());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleSpringAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        log.error("Authentication error: {}", ex.getMessage());

        String errorCode = ErrorCode.AUTHENTICATION_ERROR;
        String errorMessage = "Authentication failed";

        if (ex instanceof BadCredentialsException) {
            errorCode = ErrorCode.INVALID_CREDENTIALS;
            errorMessage = ErrorMessage.INVALID_CREDENTIALS_ERROR_MESSAGE;
        } else if (ex instanceof DisabledException) {
            errorCode = ErrorCode.EMAIL_NOT_VERIFIED;
            errorMessage = ErrorMessage.EMAIL_NOT_VERIFIED_ERROR_MESSAGE;
        } else if (ex instanceof AccountExpiredException) {
            errorCode = ErrorCode.TOKEN_EXPIRED;
            errorMessage = "Account has expired";
        } else if (ex instanceof LockedException) {
            errorCode = ErrorCode.UNAUTHORIZED;
            errorMessage = "Account is locked";
        }

        ErrorResponse response = ErrorResponse.of(errorCode, errorMessage, generateRequestId());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        log.error("Access denied: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.FORBIDDEN,
                "Access denied",
                generateRequestId());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        log.error("User not found: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.DATA_NOT_FOUND,
                ErrorMessage.DATA_NOT_FOUND_ERROR_MESSAGE,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<ErrorResponse> handleTokenValidationException(
            TokenValidationException ex, WebRequest request) {
        log.error("Token validation error: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INVALID_TOKEN,
                ErrorMessage.INVALID_TOKEN_ERROR_MESSAGE,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex, WebRequest request) {
        log.error("Email already exists: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.EMAIL_ALREADY_EXISTS,
                ErrorMessage.EMAIL_ALREADY_EXISTS_ERROR_MESSAGE,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(
            DataNotFoundException ex, WebRequest request) {
        log.error("Data not found: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getErrorMessage(),
                generateRequestId());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.error("Invalid argument: {}", ex.getMessage());

        String errorCode = ErrorCode.BAD_REQUEST;
        String errorMessage = ex.getMessage();

        if (ex.getMessage().contains("Email already registered")) {
            errorCode = ErrorCode.EMAIL_ALREADY_EXISTS;
            errorMessage = ErrorMessage.EMAIL_ALREADY_EXISTS_ERROR_MESSAGE;
        } else if (ex.getMessage().contains("Username already taken")) {
            errorCode = ErrorCode.USERNAME_ALREADY_TAKEN;
            errorMessage = ErrorMessage.USERNAME_ALREADY_TAKEN_ERROR_MESSAGE;
        } else if (ex.getMessage().contains("Passwords do not match")) {
            errorCode = ErrorCode.PASSWORDS_DO_NOT_MATCH;
            errorMessage = ErrorMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MESSAGE;
        } else if (ex.getMessage().contains("Email is already verified")) {
            errorCode = ErrorCode.EMAIL_ALREADY_VERIFIED;
            errorMessage = ErrorMessage.EMAIL_ALREADY_VERIFIED_ERROR_MESSAGE;
        } else if (ex.getMessage().contains("Invalid or expired reset token")) {
            errorCode = ErrorCode.INVALID_RESET_TOKEN;
            errorMessage = ErrorMessage.INVALID_RESET_TOKEN_ERROR_MESSAGE;
        } else if (ex.getMessage().contains("Invalid or expired verification token")) {
            errorCode = ErrorCode.INVALID_VERIFICATION_TOKEN;
            errorMessage = ErrorMessage.INVALID_VERIFICATION_TOKEN_ERROR_MESSAGE;
        }

        ErrorResponse response = ErrorResponse.of(
                errorCode,
                errorMessage,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex, WebRequest request) {
        log.error("Runtime error: {}", ex.getMessage(), ex);

        String message = ex.getMessage();
        if (message == null || message.contains("null")) {
            message = ErrorMessage.INTERNAL_SERVER_ERROR_MESSAGE;
        }

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER,
                message,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);

        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER,
                ErrorMessage.INTERNAL_SERVER_ERROR_MESSAGE,
                generateRequestId());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(
            InvalidInputException ex, WebRequest request) {
        log.error("Invalid input: {}", ex.getMessage());
        ErrorResponse response = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getErrorMessage(),
                generateRequestId());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private boolean isWebSocketRequest(WebRequest request) {
        String requestUri = request.getDescription(false);
        return requestUri != null && requestUri.contains("/ws");
    }
}