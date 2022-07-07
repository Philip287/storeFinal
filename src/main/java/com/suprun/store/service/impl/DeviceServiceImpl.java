package com.suprun.store.service.impl;

import com.suprun.store.dao.DeviceDao;
import com.suprun.store.dao.impl.DeviceDaoImpl;
import com.suprun.store.entity.Device;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceService;
import com.suprun.store.service.criteria.DeviceFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.suprun.store.entity.Device.builder;

public class DeviceServiceImpl implements DeviceService {

    private static final Logger LOGGER = LogManager.getLogger();
    private static DeviceService instance;

    private final DeviceDao deviceDao = DeviceDaoImpl.getInstance();

    private DeviceServiceImpl() {

    }

    public static DeviceService getInstance() {
        if (instance == null) {
            instance = new DeviceServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Device> findById(long id) throws ServiceException {
        try {
            return deviceDao.selectById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception in device service method find ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long insert(String name, BigDecimal price, String picturePath, String description, Device.Type type) throws ServiceException {
        Device device = builder()
                .setName(name)
                .setPrice(price)
                .setPicturePath(picturePath)
                .setDescription(description)
                .setType(type)
                .build();
        try {
            return deviceDao.insert(device);
        } catch (DaoException e) {
            LOGGER.error("Exception in device service method insert ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Device device) throws ServiceException {
        try {
            deviceDao.update(device);
        } catch (DaoException e) {
            LOGGER.error("Exception in device service method update ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            deviceDao.delete(id);
        } catch (DaoException e) {
            LOGGER.error("Exception in device service method delete ", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Device>> filter(int start, int length, DeviceFilterCriteria criteria, String keyword) throws ServiceException {
        try {
            long count;
            List<Device> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = deviceDao.selectAll(start, length);
                    count = deviceDao.selectCountAll();
                }
                case ID -> {
                    resultList = deviceDao.selectById(start, length, keyword);
                    count = deviceDao.selectCountById(keyword);
                }
                case NAME -> {
                    resultList = deviceDao.selectByName(start, length, keyword);
                    count = deviceDao.selectCountByName(keyword);
                }
                case PRICE -> {
                    resultList = deviceDao.selectByPrice(start, length, keyword);
                    count = deviceDao.selectCountByPrice(keyword);
                }
                case PICTURE_PATH -> {
                    resultList = deviceDao.selectByPicturePath(start, length, keyword);
                    count = deviceDao.selectCountByPicturePath(keyword);
                }
                case DESCRIPTION -> {
                    resultList = deviceDao.selectByDescription(start, length, keyword);
                    count = deviceDao.selectCountByDescription(keyword);
                }
                case TYPE -> {
                    resultList = deviceDao.selectByType(start, length, keyword);
                    count = deviceDao.selectCountByType(keyword);
                }
                default -> throw new ServiceException("Invalid criteria: " + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
