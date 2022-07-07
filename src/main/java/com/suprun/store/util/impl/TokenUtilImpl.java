package com.suprun.store.util.impl;

import com.suprun.store.exception.ServiceException;
import com.suprun.store.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TokenUtilImpl implements TokenUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TOKEN_PROPERTY_NAME = "properties/token.properties";
    private static final String SECRET_KEY_PROPERTY = "secretKey";
    private static final String VALIDITY_TIME_PROPERTY = "validityTime";
    public static final String ID_CLAIM = "ID";
    public static final String EMAIL_CLAIM = "email";

    private static final Key secretKey;
    public static final int validityTime;

    private static TokenUtil instance;

    static {
        ClassLoader classLoader = TokenUtilImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(TOKEN_PROPERTY_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String key = properties.getProperty(SECRET_KEY_PROPERTY);
            secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
            validityTime = Integer.parseInt(properties.getProperty(VALIDITY_TIME_PROPERTY));
        } catch (IOException e) {
            LOGGER.fatal("Couldn't read token properties file", e);
            throw new RuntimeException("Couldn't read token properties file", e);
        }
    }

    public static TokenUtil getInstance() {
        if (instance == null) {
            instance =  new TokenUtilImpl();
        }
        return instance;
    }

    @Override
    public String generateToken(Map<String, Object> claimsMap) {
        Instant expirationInstant = LocalDateTime.now(Clock.systemUTC())
                .plus(validityTime, ChronoUnit.HOURS)
                .toInstant(ZoneOffset.UTC);
        Date expirationTime = Date.from(expirationInstant);

        JwtBuilder builder = Jwts.builder()
                .signWith(secretKey)
                .setExpiration(expirationTime);
        claimsMap.forEach(builder::claim);

        return builder.compact();
    }

    @Override
    public Map<String, Object> parseToken(String token) throws ServiceException {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return new HashMap<>(claims);
        } catch (JwtException | NumberFormatException e) {
            throw new ServiceException("Got invalid token", e);
        }
    }
}
