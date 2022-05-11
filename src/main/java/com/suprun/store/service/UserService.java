package com.suprun.store.service;

import com.suprun.store.entity.User;
import com.suprun.store.entity.User.UserRole;
import com.suprun.store.entity.User.UserStatus;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.UserFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isEmailUnique(String email) throws ServiceException;

    boolean isLoginUnique(String login) throws ServiceException;

    long register(String email, String login, String password, UserRole role, UserStatus status)
            throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;

    Optional<User> findById(long id) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    void update(User user) throws ServiceException;

    void updateWithPassword(User user, String password) throws ServiceException;

    void delete(long id) throws ServiceException;

    Pair<Long, List<User>> filter(int start, int length, UserFilterCriteria criteria, String keyword)
            throws ServiceException;
}
