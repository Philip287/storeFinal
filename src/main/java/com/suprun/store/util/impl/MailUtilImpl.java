package com.suprun.store.util.impl;

import com.suprun.store.util.MailUtil;
import com.suprun.store.util.TokenUtil;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailUtilImpl implements MailUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String PROTOCOL_DELIMITER = ";//";

    private static final String MAIL_PROPERTIES_NAME = "properties/mail.properties";
    private static final String USERNAME_PROPERTY = "username";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String THREAD_POOL_SIZE_PROPERTY = "thread_pool_size";
    private static final String HTML_BODY_TYPE = "text/html; charset=UTF-8";

    private static final String CONFIRMATION_MAIL_SUBJECT_PROPERTY = "confirmationMail.subject";
    private static final String CONFIRMATION_MAIL_BODY_PROPERTY = "confirmationMail.body";
    private static final String CONFIRMATION_MAIL_URL_BLANK = "controller?command=confirm_email&token=";

    private static final String PASSWORD_CHANGE_MAIL_SUBJECT_PROPERTY = "passwordChangeMail.subject";
    private static final String PASSWORD_CHANGE_MAIL_BODY_PROPERTY = "passwordChangeMAil.body";
    private static final String PASSWORD_CHANGE_MAIL_URL_BLANK = "/controller?command=go_to_password_change_page&token=";

    private static final String ORDER_IN_PROGRESS_MAIL_SUBJECT_PROPERTY = "orderInProgressMail.subject";
    private static final String ORDER_IN_PROGRESS_MAIL_BODY_PROPERTY = "orderInProgressMail.body";

    private static final String ORDER_COMPLETED_MAIL_SUBJECT_PROPERTY = "orderCompletedMail.subject";
    private static final String ORDER_COMPLETED_MAIL_BODY_PROPERTY = "orderCompletedMail.body";

    private static final Properties mailProperties;
    private static final Session mailSession;
    private static final String sender;

    private static final ExecutorService emailExecutor;
    private static final TokenUtil tokenUtil = TokenUtilImpl.getInstance();

    private static MailUtil instance;

    static {
        ClassLoader classLoader = MailUtilImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(MAIL_PROPERTIES_NAME)) {
            mailProperties = new Properties();
            mailProperties.load(inputStream);
            sender = mailProperties.getProperty(USERNAME_PROPERTY);
            String password = mailProperties.getProperty(PASSWORD_PROPERTY);
            int threadPoolSize = Integer.parseInt(mailProperties.getProperty(THREAD_POOL_SIZE_PROPERTY));

            mailSession = Session.getInstance(mailProperties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });
            emailExecutor = Executors.newFixedThreadPool(threadPoolSize);
        } catch (IOException e) {
            LOGGER.fatal("Couldn't read mail properties file", e);
            throw new RuntimeException("Couldn't read mail properties file", e);
        }
    }

    public static MailUtil getInstance() {
        if (instance == null) {
            instance = new MailUtilImpl();
        }
        return instance;
    }

    @Override
    public void SendConfirmationMail(long userId, String email, String scheme, String serverName) throws SerialException {

    }

    @Override
    public void sendPasswordChangeMail(String email, String scheme, String serverName) throws SerialException {

    }

    @Override
    public void sendOrderInProgressMail(String email, String scheme, String serverName) throws SerialException {

    }

    @Override
    public void sendOrderCompleteMail(String email, String scheme, String serverName) throws SerialException {

    }
}
