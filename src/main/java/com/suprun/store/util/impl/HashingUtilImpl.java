package com.suprun.store.util.impl;

import com.suprun.store.exception.ServiceException;
import com.suprun.store.util.HashingUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Properties;

public class HashingUtilImpl implements HashingUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static HashingUtil instance;

    private static final String HASHING_PROPERTIES_NAME = "properties/hashing.properties";
    private static final String ITERATION_COUNT_PROPERTY = "iterationCount";
    private static final String KEY_LENGTH_PROPERTY = "keyLength";
    private static final String ALGORITHM_PROPERTY = "algorithm";
    private static final String SALT_LENGTH_PROPERTY = "saltLength";
    private static final byte[] CONST_SALT = "GHafjl214lnsgk0h".getBytes(StandardCharsets.UTF_8);

    private static final int iterationCount;
    private static final int keyLength;
    private static final String hashingAlgorithm;
    private static final int saltLength;

    static {
        ClassLoader classLoader = HashingUtilImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(HASHING_PROPERTIES_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            iterationCount = Integer.parseInt(properties.getProperty(ITERATION_COUNT_PROPERTY));
            keyLength = Integer.parseInt(properties.getProperty(KEY_LENGTH_PROPERTY));
            hashingAlgorithm = properties.getProperty(ALGORITHM_PROPERTY);
            saltLength = Integer.parseInt(properties.getProperty(SALT_LENGTH_PROPERTY));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Couldn't read hashing properties file", e);
            throw new RuntimeException("Couldn't read hashing properties file", e);
        }
    }

    public static HashingUtil getInstance() {
        if (instance == null) {
            instance = new HashingUtilImpl();
        }
        return instance;
    }

    @Override
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLength];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] generateHash(String password, byte[] salt) throws ServiceException {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(hashingAlgorithm);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            byte[] result = new byte[hash.length + CONST_SALT.length];
            System.arraycopy(hash, 0, result, 0, hash.length);
            System.arraycopy(CONST_SALT, 0, result, hash.length, CONST_SALT.length);
            return result;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException("Error generating hash", e);
        }
    }
}
