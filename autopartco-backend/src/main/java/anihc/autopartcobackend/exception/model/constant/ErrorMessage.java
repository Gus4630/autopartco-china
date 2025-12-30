package anihc.autopartcobackend.exception.model.constant;

public interface ErrorMessage {

    String REQUIRED_NULL_ERROR_MESSAGE = "{0} must be null";
    String REQUIRED_NON_NULL_ERROR_MESSAGE = "{0} must not be null";
    String DATA_NOT_FOUND_ERROR_MESSAGE = "Data not found with {0} - {1}";
    String INTERNAL_SERVER_ERROR_MESSAGE = "Unexpected internal server error occurs";
    String UNIQUE_ERROR_MESSAGE = "{0} must be unique.";
    String UNPROCESSABLE_ERROR_MESSAGE = "Unprocessable entity";
    String INVALID_VALUE_ERROR_MESSAGE_SINGLE = "Invalid value for field: {0}";
    String INVALID_VALUE_ERROR_MESSAGE = "Invalid value for field: {0} - {1}";
    String CONSTRAINT_VIOLATION_ERROR_MESSAGE = "Constraint violation error occurred.";
    String ALREADY_EXISTS_ERROR_MESSAGE = "Resource already exists.";
    String GENERAL_FILE_CONFIFURATION_EXISTS_MESSAGE = "Only one active GENERAL configuration is allowed";
    String PROJECT_NOT_FOUND_ERROR_MESSAGE = "Project not found with {0} - {1}";

    // Authentication error messages
    String INVALID_CREDENTIALS_ERROR_MESSAGE = "Invalid email or password";
    String EMAIL_NOT_VERIFIED_ERROR_MESSAGE = "Email not verified";
    String TOKEN_EXPIRED_ERROR_MESSAGE = "Token has expired";
    String INVALID_TOKEN_ERROR_MESSAGE = "Invalid token";

    // Validation error messages
    String EMAIL_ALREADY_EXISTS_ERROR_MESSAGE = "Email already registered";
    String USERNAME_ALREADY_TAKEN_ERROR_MESSAGE = "Username already taken";
    String PASSWORDS_DO_NOT_MATCH_ERROR_MESSAGE = "Passwords do not match";
    String EMAIL_ALREADY_VERIFIED_ERROR_MESSAGE = "Email is already verified";
    String INVALID_RESET_TOKEN_ERROR_MESSAGE = "Invalid or expired reset token";
    String INVALID_VERIFICATION_TOKEN_ERROR_MESSAGE = "Invalid or expired verification token";
    String USER_NOT_FOUND = "User not found";
    String INVALID_INPUT_ERROR_MESSAGE = "Invalid input provided";

}