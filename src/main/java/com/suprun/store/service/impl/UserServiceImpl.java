package com.suprun.store.service.impl;

import com.suprun.store.dao.UserDao;
import com.suprun.store.dao.impl.UserDaoImpl;
import com.suprun.store.entity.User;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.criteria.UserFilterCriteria;
import com.suprun.store.util.HashingUtil;
import com.suprun.store.util.impl.HashingUtilImpl;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

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
            throw new ServiceException(e);
        }
        return user.isEmpty();
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.selectByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user.isEmpty();
    }

    @Override
    public long register(String email, String login, String password, User.UserRole role, User.UserStatus status) throws ServiceException {
        byte[] salt = hashingUtil.generateSalt();
        byte[] passwordHash = hashingUtil.generateHash(password, salt);

        User user = (User) User.builder()
                .setEmail(email)
                .setEmail(email)
                .setPasswordHash(passwordHash)
                .setSalt(salt)
                .setRole(role)
                .setStatus(status)
                .build();
        try {
            return userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optional;
        try {
            optional = userDao.selectByLogin(login);
            if (optional.isPresent()) {
                User user = optional.get();
                byte[] passwordHash = hashingUtil.generateHash(password, user.getSalt());

                if (Arrays.equals(passwordHash, user.getPasswordHash())) {
                    return Optional.of(user);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        try {
            return userDao.selectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.selectByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }
    }
}
