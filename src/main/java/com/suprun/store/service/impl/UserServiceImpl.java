package com.suprun.store.service.impl;

import com.suprun.store.dao.UserDao;
import com.suprun.store.dao.impl.UserDaoImpl;
import com.suprun.store.service.UserService;
import com.suprun.store.service.criteria.UserFilterCriteria;
import com.suprun.store.entity.User;
import com.suprun.store.entity.User.UserStatus;
import com.suprun.store.entity.User.UserRole;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.util.HashingUtil;
import com.suprun.store.util.impl.HashingUtilImpl;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final HashingUtil hashingUtil = HashingUtilImpl.getInstance();
    private final UserDao userDao = UserDaoImpl.getInstance();

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.selectByEmail(email);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during isEmailUnique ", e);
            throw new ServiceException("Error in user service during isEmailUnique ", e);
        }
        return user.isEmpty();
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.selectByLogin(login);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during isLoginUnique ", e);
            throw new ServiceException("Error in user service during isLoginUnique ", e);
        }
        return user.isEmpty();
    }

    @Override
    public long register(String email, String login, String password, UserRole role, UserStatus status) throws ServiceException {
        byte[] salt = hashingUtil.generateSalt();
        byte[] passwordHash = hashingUtil.generateHash(password, salt);

        User user = (User) User.builder()
                .setEmail(email)
                .setLogin(login)
                .setPasswordHash(passwordHash)
                .setSalt(salt)
                .setRole(role)
                .setStatus(status)
                .build();
        try {
            return userDao.insert(user);

        } catch (DaoException e) {
            LOGGER.error("Error in user service during register ", e);
            throw new ServiceException("Error in user service during register ", e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.selectByLogin(login);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                byte[] passwordHash = hashingUtil.generateHash(password, user.getSalt());

                if (Arrays.equals(passwordHash, user.getPasswordHash())) {
                    return Optional.of(user);
                }
            }
        } catch (DaoException e) {
            LOGGER.error("Error in user service during login", e);
            throw new ServiceException("Error in user service during login", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        try {
            return userDao.selectById(id);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during findById", e);
            throw new ServiceException("Error in user service during findById", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.selectByEmail(email);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during findByEmail", e);
            throw new ServiceException("Error in user service during findByEmail", e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during update", e);
            throw new ServiceException("Error in user service during update", e);
        }
    }

    @Override
    public void updateWithPassword(User user, String password) throws ServiceException {
        byte[] salt = hashingUtil.generateSalt();
        byte[] passwordHash = hashingUtil.generateHash(password, salt);

        User updateUser = (User) User.builder().of(user)
                .setSalt(salt)
                .setPasswordHash(passwordHash)
                .build();

        try {
            userDao.update(updateUser);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during updateWithPassword", e);
            throw new ServiceException("Error in user service during updateWithPassword", e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during delete", e);
            throw new ServiceException("Error in user service during delete", e);
        }
    }

    @Override
    public Pair<Long, List<User>> filter(int start, int length, UserFilterCriteria criteria, String keyword)
            throws ServiceException {
        long count;
        List<User> resultList;
        try {
            switch (criteria) {
                case NONE -> {
                    resultList = userDao.selectAll(start, length);
                    count = userDao.selectCountAll();
                }
                case ID -> {
                    resultList = userDao.selectById(start, length, keyword);
                    count = userDao.selectCountById(keyword);
                }
                case EMAIL -> {
                    resultList = userDao.selectByEmail(start, length, keyword);
                    count = userDao.selectCountByEmail(keyword);
                }
                case LOGIN -> {
                    resultList = userDao.selectByLogin(start, length, keyword);
                    count = userDao.selectCountByLogin(keyword);
                }
                case ROLE -> {
                    resultList = userDao.selectByRole(start, length, keyword);
                    count = userDao.selectCountByRole(keyword);
                }
                case STATUS -> {
                    resultList = userDao.selectByStatus(start, length, keyword);
                    count = userDao.selectCountByStatus(keyword);
                }
                default -> throw new ServiceException("Invalid criteria: " + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            LOGGER.error("Error in user service during filter", e);
            throw new ServiceException("Error in user service during filter", e);
        }
    }
}
