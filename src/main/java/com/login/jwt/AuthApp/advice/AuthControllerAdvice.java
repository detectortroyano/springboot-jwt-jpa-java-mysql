package com.login.jwt.AuthApp.advice;

import com.login.jwt.AuthApp.exception.AppException;
import com.login.jwt.AuthApp.exception.BadRequestException;
import com.login.jwt.AuthApp.exception.InvalidTokenRequestException;
import com.login.jwt.AuthApp.exception.MailSendException;
import com.login.jwt.AuthApp.exception.PasswordResetException;
import com.login.jwt.AuthApp.exception.PasswordResetLinkException;
import com.login.jwt.AuthApp.exception.ResourceAlreadyInUseException;
import com.login.jwt.AuthApp.exception.ResourceNotFoundException;
import com.login.jwt.AuthApp.exception.TokenRefreshException;
import com.login.jwt.AuthApp.exception.UpdatePasswordException;
import com.login.jwt.AuthApp.exception.UserLoginException;
import com.login.jwt.AuthApp.exception.UserLogoutException;
import com.login.jwt.AuthApp.exception.UserRegistrationException;
import com.login.jwt.AuthApp.model.payload.ApiResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthControllerAdvice {

    private static final Logger logger = Logger.getLogger(AuthControllerAdvice.class);

    private final MessageSource messageSource;

    @Autowired
    public AuthControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setData(processAllErrors(allErrors).stream().collect(Collectors.joining("\n")));
        return response;
    }

    /**
     * Utility Method to generate localized message for a list of field errors
     *
     * @param allErrors the field errors
     * @return the list
     */
    private List<String> processAllErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
    }

    /**
     * Resolve localized error message. Utiity method to generate a localized error
     * message
     *
     * @param objectError the field error
     * @return the string
     */
    private String resolveLocalizedErrorMessage(ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        logger.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleAppException(AppException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = ResourceAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    @ResponseBody
    public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleBadRequestException(BadRequestException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = UserLoginException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserLoginException(UserLoginException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleBadCredentialsException(BadCredentialsException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = UserRegistrationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserRegistrationException(UserRegistrationException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = PasswordResetLinkException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handlePasswordResetLinkException(PasswordResetLinkException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = PasswordResetException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handlePasswordResetException(PasswordResetException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = MailSendException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ApiResponse handleMailSendException(MailSendException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = InvalidTokenRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiResponse handleInvalidTokenException(InvalidTokenRequestException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = UpdatePasswordException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUpdatePasswordException(UpdatePasswordException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }


    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleTokenRefreshException(TokenRefreshException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = UserLogoutException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserLogoutException(UserLogoutException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setData(ex.getMessage());
        return apiResponse;
    }

}
