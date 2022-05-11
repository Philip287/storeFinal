package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;

import java.util.Map;

public interface TokenUtil {
    String generateToken(Map<String, Object> claimsMap);
    Map<String, Object> parseToken(String token) throws ServiceException;
}
