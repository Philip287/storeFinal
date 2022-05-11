package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;

public interface HashingUtil {
    byte[] generateSalt();

    byte[] generateHash(String password, byte[] salt) throws ServiceException;
}
