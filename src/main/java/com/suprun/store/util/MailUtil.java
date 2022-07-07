package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;

/**
 * {@code MailUtil} interface provides functionality for sending emails.
 *
 * @author Philip Suprun
 */
public interface MailUtil {

    /**
     * Used to send email with user confirmation link.
     *
     * @param userId     claim
     * @param email      to which email is sent
     * @param scheme     from request
     * @param serverName from request
     * @throws ServiceException if application is unable to send mail
     */
    void SendConfirmationMail(long userId, String email, String scheme, String serverName) throws ServiceException;

    /**
     * Used to send email with password change link.
     *
     * @param email      to which email is sent
     * @param scheme     from request
     * @param serverName from request
     * @throws ServiceException if application is unable to send mail
     */
    void sendPasswordChangeMail(String email, String scheme, String serverName) throws ServiceException;

    /**
     * Used to send notification email about order status change to "In progress".
     *
     * @param email      to which email is sent
     * @param orderId    of order
     * @param scheme     from request
     * @param serverName from request
     * @throws ServiceException if application is unable to send mail
     */
    void sendOrderInProgressMail(String email, long orderId, String scheme, String serverName) throws ServiceException;

    /**
     * Used to send notification email about order status change to "Completed".
     *
     * @param email      to which email is sent
     * @param orderId    of order
     * @param scheme     from request
     * @param serverName from request
     * @throws ServiceException if application is unable to send mail
     */
    void sendOrderCompleteMail(String email, long orderId, String scheme, String serverName) throws ServiceException;
}
