package com.suprun.store.service.impl;

import com.suprun.store.dao.UserDao;
import com.suprun.store.dao.impl.UserDaoImpl;
import com.suprun.store.entity.User;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.criteria.UserFilterCriteria;
import com.suprun.store.util.HashingUtil;
import com.suprun.store.util.impl.HashingUtilImpl;
import org.apache.commons.lang3.tuple.Pair;

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

    //FIXME
    @Override
    public boolean isEmailUnique(String email) throws ServiceException {
        return false;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        return false;
    }

    @Override
    public long register(String email, String login, String password, User.UserRole role, User.UserStatus status) throws ServiceException {
        return 0;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public void update(User user) throws ServiceException {

    }

    @Override
    public void updateWithPassword(User user, String password) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    @Override
    public Pair<Long, List<User>> filter(int start, int length, UserFilterCriteria criteria, String keyword) throws ServiceException {
        return null;
    }
}
