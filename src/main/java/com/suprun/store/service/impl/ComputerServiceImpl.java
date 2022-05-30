package com.suprun.store.service.impl;

import com.suprun.store.dao.ComputerDao;
import com.suprun.store.dao.impl.ComputerDaoImpl;
import com.suprun.store.entity.Computer;
import com.suprun.store.entity.Computer.OrderStatus;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.ComputerService;
import com.suprun.store.service.criteria.ComputerFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.suprun.store.entity.Computer.builder;

public class ComputerServiceImpl implements ComputerService {

    private static ComputerService instance;

    private final ComputerDao computerDao = ComputerDaoImpl.getInstance();

    public static ComputerService getInstance() {
        if (instance == null) {
            instance = new ComputerServiceImpl();
        }
        return instance;
    }

    private ComputerServiceImpl() {

    }

    @Override
    public Optional<Computer> findById(long id) throws ServiceException {
        try {
            return computerDao.selectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long insert(OrderStatus status, String name, BigDecimal totalCoast, long userId, long processorId, long videoCardId,
                       long motherboardId, long ramId, long powerSupplyId, long keyboardId, long monitorId, long mouseId,
                       long hullId, long hddId, long speakerId) throws ServiceException {
        Computer computer = builder()
                .setName(name)
                .setUserId(userId)
                .setTotalCoast(totalCoast)
                .setSpeakerId(speakerId)
                .setMonitorId(monitorId)
                .setPowerSupplyId(powerSupplyId)
                .setRamId(ramId)
                .setMotherboardId(motherboardId)
                .setVideoCardId(videoCardId)
                .setProcessorId(processorId)
                .setHddId(hddId)
                .setKeyboardId(keyboardId)
                .setOrderStatus(status)
                .setMouseId(mouseId)
                .setHullId(hullId)
                .build();
        try {
            return computerDao.insert(computer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Computer computer) throws ServiceException {
        try {
            computerDao.update(computer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            computerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Computer>> filter(int start, int length, ComputerFilterCriteria criteria, String keyword)
            throws ServiceException {
        try {
            long count;
            List<Computer> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = computerDao.selectAll(start, length);
                    count = computerDao.selectCountAll();
                }
                case USER_ID -> {
                    resultList = computerDao.selectByUserId(start, length, keyword);
                    count = computerDao.selectCountByUserId(keyword);
                }
                case ID -> {
                    resultList = computerDao.selectById(start, length, keyword);
                    count = computerDao.selectCountById(keyword);
                }
                case NAME -> {
                    resultList = computerDao.selectByName(start, length, keyword);
                    count = computerDao.selectCountByName(keyword);
                }
                case ORDER_STATUS -> {
                    resultList = computerDao.selectByOrderStatus(start, length, keyword);
                    count = computerDao.selectCountByOrderStatus(keyword);
                }
                case TOTAL_COST -> {
                    resultList = computerDao.selectByTotalCoast(start, length, keyword);
                    count = computerDao.selectCountByTotalCoast(keyword);
                }
                case PROCESSOR_ID -> {
                    resultList = computerDao.selectByProcessorId(start, length, keyword);
                    count = computerDao.selectCountByProcessorId(keyword);
                }
                case VIDEO_CARD_ID -> {
                    resultList = computerDao.selectByVideoCardId(start, length, keyword);
                    count = computerDao.selectCountByVideoCardId(keyword);
                }
                case MOTHERBOARD_ID -> {
                    resultList = computerDao.selectByMotherBoardId(start, length, keyword);
                    count = computerDao.selectCountByMotherBoardId(keyword);
                }
                case RAM_ID -> {
                    resultList = computerDao.selectByRamId(start, length, keyword);
                    count = computerDao.selectCountByRamId(keyword);
                }
                case POWER_SUPPLY_ID -> {
                    resultList = computerDao.selectByPowerSupplyId(start, length, keyword);
                    count = computerDao.selectCountByPowerSupplyId(keyword);
                }
                case KEYBOARD_ID -> {
                    resultList = computerDao.selectByKeyboardId(start, length, keyword);
                    count = computerDao.selectCountByKeyboardId(keyword);
                }
                case MONITOR_ID -> {
                    resultList = computerDao.selectByMonitorId(start, length, keyword);
                    count = computerDao.selectCountByMonitorId(keyword);
                }
                case MOUSE_ID -> {
                    resultList = computerDao.selectByMouseId(start, length, keyword);
                    count = computerDao.selectCountByMouseId(keyword);
                }
                case HULL_ID -> {
                    resultList = computerDao.selectByHullId(start, length, keyword);
                    count = computerDao.selectCountByHullId(keyword);
                }
                case HDD_ID -> {
                    resultList = computerDao.selectByHddId(start, length, keyword);
                    count = computerDao.selectCountByHddId(keyword);
                }
                case SPEAKER_ID -> {
                    resultList = computerDao.selectBySpeakerId(start, length, keyword);
                    count = computerDao.selectCountBySpeakerId(keyword);
                }
                default -> throw new ServiceException("Invalid criteria: " + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Computer>> filterForActiveOrder(int start, int length, ComputerFilterCriteria criteria,
                                                           String keyword) throws ServiceException {
        try {
            long count;
            List<Computer> resultList;
            switch (criteria) {
                case NONE -> {
                    resultList = computerDao.selectAllForActiveOrder(start, length);
                    count = computerDao.selectCountAllForActiveOrder(keyword);
                }
                case USER_ID -> {
                    resultList = computerDao.selectByUserIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByUserIdForActiveOrder(keyword);
                }
                case ID -> {
                    resultList = computerDao.selectByIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByIdForActiveOrder(keyword);
                }
                case NAME -> {
                    resultList = computerDao.selectByNameForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByNameForActiveOrder(keyword);
                }
                case ORDER_STATUS -> {
                    resultList = computerDao.selectByOrderStatusForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByOrderStatusForActiveOrder(keyword);
                }
                case TOTAL_COST -> {
                    resultList = computerDao.selectByTotalCoastForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByTotalCoastForActiveOrder(keyword);
                }
                case PROCESSOR_ID -> {
                    resultList = computerDao.selectByProcessorIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByProcessorIdForActiveOrder(keyword);
                }
                case VIDEO_CARD_ID -> {
                    resultList = computerDao.selectByVideoCardIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByVideoCardIdForActiveOrder(keyword);
                }
                case MOTHERBOARD_ID -> {
                    resultList = computerDao.selectByMotherBoardIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByMotherBoardIdForActiveOrder(keyword);
                }
                case RAM_ID -> {
                    resultList = computerDao.selectByRamIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByRamIdForActiveOrder(keyword);
                }
                case POWER_SUPPLY_ID -> {
                    resultList = computerDao.selectByPowerSupplyIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByPowerSupplyIdForActiveOrder(keyword);
                }
                case KEYBOARD_ID -> {
                    resultList = computerDao.selectByKeyboardIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByKeyboardIdForActiveOrder(keyword);
                }
                case MONITOR_ID -> {
                    resultList = computerDao.selectByMonitorIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByMonitorIdForActiveOrder(keyword);
                }
                case MOUSE_ID -> {
                    resultList = computerDao.selectByMouseIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByMouseIdForActiveOrder(keyword);
                }
                case HULL_ID -> {
                    resultList = computerDao.selectByHullIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByHullIdForActiveOrder(keyword);
                }
                case HDD_ID -> {
                    resultList = computerDao.selectByHddIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountByHddIdForActiveOrder(keyword);
                }
                case SPEAKER_ID -> {
                    resultList = computerDao.selectBySpeakerIdForActiveOrder(start, length, keyword);
                    count = computerDao.selectCountBySpeakerIdForActiveOrder(keyword);
                }
                default -> throw new ServiceException("Invalid criteria" + criteria);
            }
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Pair<Long, List<Computer>> filterByNameForUser(int start, int length, String keyword, String userId) throws ServiceException {
        try {
            List<Computer> resultList = computerDao.selectByNameForUser(start, length, keyword, userId);
            long count = computerDao.selectCountByNameForUser(keyword, userId);
            return Pair.of(count, resultList);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
