package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;

public interface MailUtil {
    void SendConfirmationMail(long userId, String email, String scheme, String serverName) throws ServiceException;

    void sendPasswordChangeMail(String email, String scheme, String serverName) throws ServiceException;

    void sendOrderInProgressMail(String email, String scheme, String serverName) throws ServiceException;

    void sendOrderCompleteMail(String email, String scheme, String serverName) throws ServiceException;
}
