package anihc.autopartcobackend.exception.model.constant;

public interface ErrorCode {

    String CONFLICT = "conflict";
    String BAD_REQUEST = "bad_request";
    String FORBIDDEN = "forbidden";
    String UNAUTHORIZED = "unauthorized";
    String ALREADY_EXIST = "already_exist";
    String DATA_NOT_FOUND = "data_not_found";
    String RESOURCE_MISSING = "resource_missing";
    String PARAMETER_INVALID = "parameter_invalid";
    String REQUEST_INVALID = "request_body_invalid";
    String INTERNAL_SERVER = "unexpected_internal_error";
    String UNIQUE_ERROR_CODE = "unique_violation";
    String UNPROCESSABLE_ENTITY_CODE = "unprocessable_entity";
    String AUTHENTICATION_ERROR = "authentication_error";
    String INVALID_CREDENTIALS = "invalid_credentials";
    String EMAIL_NOT_VERIFIED = "email_not_verified";
    String TOKEN_EXPIRED = "token_expired";
    String INVALID_TOKEN = "invalid_token";
    String VALIDATION_ERROR = "validation_error";
    String EMAIL_ALREADY_EXISTS = "email_already_exists";
    String USERNAME_ALREADY_TAKEN = "username_already_taken";
    String PASSWORDS_DO_NOT_MATCH = "passwords_do_not_match";
    String EMAIL_ALREADY_VERIFIED = "email_already_verified";
    String INVALID_RESET_TOKEN = "invalid_reset_token";
    String INVALID_VERIFICATION_TOKEN = "invalid_verification_token";
}