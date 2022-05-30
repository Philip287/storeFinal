package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;
import org.apache.commons.lang3.tuple.Pair;

public interface ValidationUtil {

    Pair<Boolean, String> isUpdatedUserDuplicate(String email, String login, String redirectUrl, boolean emailsMatch,
                                                 boolean loginsMatch) throws ServiceException;

    Pair<Boolean, String> isUserDuplicate(String email, String login, String redirectUrl) throws ServiceException;
}
