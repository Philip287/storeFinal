package com.suprun.store.service.impl;

import com.suprun.store.dao.DeviceHasOrderDao;
import com.suprun.store.dao.impl.DeviceHasOrderDaoImpl;
import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceHasOrderService;
import com.suprun.store.service.criteria.DeviceHasOrderFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


import static com.suprun.store.entity.DeviceHasOrder.builder;

public class DeviceHasOrderServiceImpl implements DeviceHasOrderService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static DeviceHasOrderService instance;

    private final DeviceHasOrderDao deviceHasOrderDao = DeviceHasOrderDaoImpl.getInstance();

    private DeviceHasOrderServiceImpl() {

    }

    public static DeviceHasOrderService getInstance() {
        if (instance == null) {
            instance = new DeviceHasOrderServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<DeviceHasOrder> findById(long id) throws ServiceException {
        try {
            return deviceHasOrderDao.selectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long insert(long deviceId, long orderId, long number) throws ServiceException {
        DeviceHasOrder deviceHasOrder = builder()
                .setDeviceId(deviceId)
                .setOrderId(orderId)
                .setNumber(number)
                .build();
        try {
            return deviceHasOrderDao.insert(deviceHasOrder);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(DeviceHasOrder deviceHasOrder) throws ServiceException {
        try {
            deviceHasOrderDao.update(deviceHasOrder);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            deviceHasOrderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<DeviceHasOrder>> filter(int start, int length, DeviceHasOrderFilterCriteria criteria, String keyword) throws ServiceException {
        try {
            long count;
            List<DeviceHasOrder> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = deviceHasOrderDao.selectAll(start, length);
                    count = deviceHasOrderDao.selectCountAll();
                }
                case ID -> {
                    resultList = deviceHasOrderDao.selectById(start, length, keyword);
                    count = deviceHasOrderDao.selectCountById(keyword);
                }
                case ID_DEVICE -> {
                    resultList = deviceHasOrderDao.selectByDeviceId(start, length, keyword);
                    count = deviceHasOrderDao.selectCountByDeviceId(keyword);
                }
                case NUMBER -> {
                    resultList = deviceHasOrderDao.selectByNumber(start, length, keyword);
                    count = deviceHasOrderDao.selectCountByNumber(keyword);
                }
                default -> throw new ServiceException("Invalid criteria: " + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            LOGGER.error("Exception in filter DeviceHasOrderServiceImpl", e);
            throw new ServiceException("Exception in filter DeviceHasOrderServiceImpl", e);
        }
    }
}
