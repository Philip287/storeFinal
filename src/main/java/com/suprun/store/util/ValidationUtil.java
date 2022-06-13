package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;
import org.apache.commons.lang3.tuple.Pair;

/**
 * {@code ValidationUtil} interface provides functionality for grouping validation tasks.
 *
 * @author Philip Suprun
 */
public interface ValidationUtil {


    /**
     * Check if user with such email or login exists in database.
     * Doesn't check existence of email if {@code emailsMatch = true}.
     * Doesn't check existence of login if {@code loginsMatch = true}.
     *
     * @param email       to check
     * @param login       to check
     * @param redirectUrl containing command to redirect to
     * @param emailsMatch boolean
     * @param loginsMatch boolean
     * @return {@link Pair} of boolean and string with url with error parameters
     * @throws ServiceException if application is unable to check in database
     */
    Pair<Boolean, String> isUpdatedUserDuplicate(String email, String login, String redirectUrl, boolean emailsMatch,
                                                 boolean loginsMatch) throws ServiceException;

    /**
     * Check if user with such email or login exists in database.
     *
     * @param email       to check
     * @param login       to check
     * @param redirectUrl containing command to redirect to
     * @return {@link Pair} of boolean and string with url with error parameters
     * @throws ServiceException if application is unable to check in database
     */
    Pair<Boolean, String> isUserDuplicate(String email, String login, String redirectUrl) throws ServiceException;
}
