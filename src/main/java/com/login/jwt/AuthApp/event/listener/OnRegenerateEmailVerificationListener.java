package com.login.jwt.AuthApp.event.listener;

import com.login.jwt.AuthApp.event.OnRegenerateEmailVerificationEvent;
import com.login.jwt.AuthApp.exception.MailSendException;
import com.login.jwt.AuthApp.model.User;
import com.login.jwt.AuthApp.model.token.EmailVerificationToken;
import com.login.jwt.AuthApp.service.MailService;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class OnRegenerateEmailVerificationListener implements ApplicationListener<OnRegenerateEmailVerificationEvent> {

    private static final Logger logger = Logger.getLogger(OnRegenerateEmailVerificationListener.class);
    private final MailService mailService;

    @Autowired
    public OnRegenerateEmailVerificationListener(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * As soon as a registration event is complete, invoke the email verification
     */
    @Override
    @Async
    public void onApplicationEvent(OnRegenerateEmailVerificationEvent onRegenerateEmailVerificationEvent) {
        resendEmailVerification(onRegenerateEmailVerificationEvent);
    }

    /**
     * Send email verification to the user and persist the token in the database.
     */
    private void resendEmailVerification(OnRegenerateEmailVerificationEvent event) {
        User user = event.getUser();
        EmailVerificationToken emailVerificationToken = event.getToken();
        String recipientAddress = user.getEmail();

        String emailConfirmationUrl =
                event.getRedirectUrl().queryParam("token", emailVerificationToken.getToken()).toUriString();
        try {
            mailService.sendEmailVerification(emailConfirmationUrl, recipientAddress);
        } catch (IOException | TemplateException | MessagingException e) {
            logger.error(e);
            throw new MailSendException(recipientAddress, "Email Verification");
        }
    }

}
