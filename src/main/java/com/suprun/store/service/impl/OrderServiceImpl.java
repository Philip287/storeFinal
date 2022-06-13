package com.suprun.store.service.impl;

import com.suprun.store.dao.OrderDao;
import com.suprun.store.dao.impl.OrderDaoImpl;
import com.suprun.store.entity.Order;
import com.suprun.store.entity.Order.OrderStatus;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.criteria.OrderFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static OrderService instance;

    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {

    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Order> findById(long id) throws ServiceException {
        try {
            return orderDao.selectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long insert(OrderStatus status, Long userId) throws ServiceException {
        Order order = Order.builder()
                .setOrderStatus(status)
                .setUserId(userId)
                .build();
        try {
            return orderDao.insert(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Order>> filter(int start, int length, OrderFilterCriteria criteria, String keyword) throws ServiceException {
        try {
            long count;
            List<Order> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = orderDao.selectAll(start, length);
                    count = orderDao.selectCountAll();
                }
                case ID -> {
                    resultList = orderDao.selectById(start, length, keyword);
                    count = orderDao.selectCountById(keyword);
                }
                case ORDER_STATUS -> {
                    resultList = orderDao.selectByOrderStatus(start, length, keyword);
                    count = orderDao.selectCountByOrderStatus(keyword);
                }
                case ID_USER -> {
                    resultList = orderDao.selectByUserId(start, length, keyword);
                    count = orderDao.selectCountByUserId(keyword);
                }
                default -> throw new ServiceException("Invalid criteria" + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Order>> filterForActiveOrder(int start, int length, OrderFilterCriteria criteria, String keyword) throws ServiceException {
        try {
            long count;
            List<Order> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = orderDao.selectAllForActiveOrder(start, length);
                    count = orderDao.selectCountAllForActiveOrder();
                }
                case ORDER_STATUS -> {
                    resultList = orderDao.selectByOrderStatus(start, length, keyword);
                    count = orderDao.selectCountByOrderStatus(keyword);
                }
                case ID_USER -> {
                    resultList = orderDao.selectByUserIdForActiveOrder(start, length, keyword);
                    count = orderDao.selectCountByUserIdForActiveOrder(keyword);
                }
                default -> throw new ServiceException("Invalid criteria: " + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
