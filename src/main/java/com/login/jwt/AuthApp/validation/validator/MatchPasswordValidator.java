package com.login.jwt.AuthApp.validation.validator;

import com.login.jwt.AuthApp.model.payload.PasswordResetRequest;
import com.login.jwt.AuthApp.validation.annotation.MatchPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, PasswordResetRequest> {

    private Boolean allowNull;

    @Override
    public void initialize(MatchPassword constraintAnnotation) {
        allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(PasswordResetRequest value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();
        if (allowNull) {
            return null == password && null == confirmPassword;
        }
        return password.equals(confirmPassword);
    }
}
