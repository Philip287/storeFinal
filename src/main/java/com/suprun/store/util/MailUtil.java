package com.suprun.store.util;

import javax.sql.rowset.serial.SerialException;

public interface MailUtil {
    void SendConfirmationMail(long userId, String email, String scheme, String serverName) throws SerialException;

    void sendPasswordChangeMail(String email, String scheme, String serverName) throws SerialException;

    void sendOrderInProgressMail(String email, String scheme, String serverName) throws SerialException;

    void sendOrderCompleteMail(String email, String scheme, String serverName) throws SerialException;
}
