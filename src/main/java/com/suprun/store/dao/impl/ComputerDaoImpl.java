package com.suprun.store.dao.impl;

import com.suprun.store.dao.ComputerDao;
import com.suprun.store.entity.Computer;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComputerDaoImpl implements ComputerDao {
    // FIXME: 27/05/2022
    private static ComputerDao instance;

    private static final String INSERT = """
            INSERT INTO computers(id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard,
            id_ram, id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE computers
            SET id_user = ?, total_coast = ?, order_status = ?, id_processor = ?, id_video_card = ?, id_motherboard = ?,
            id_ram = ?, id_power_supply = ?, id_keyboard = ?, id_monitor = ?, id_mouse = ?, id_hull = ?, id_hdd = ?, 
            id_speaker = ?, name = ?
            WHERE computer_id = ?;
            """;

    private static final String DELETE = """
            UPDATE computers
            SET deleted = 1
            WHERE computer_id = ?;
            """;

    private static final String SELECT_BY_ID = """
            SELECT id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard,
            id_ram, id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE computer_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard,
            id_ram, id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE computer_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE computers_id LIKE CONCAT('%', ?, '?') AND deleted <> 1;
            """;

    private static final String SELECT_ALL = """
            SELECT computer_id, id_user, total_cost, deleted, order_status, id_processor, id_video_card, id_mother_board,
            id_ram, id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE deleted <> 1:
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE name LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT(computer_id)
            FROM computer
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;


    public static ComputerDao getInstance() {
        if (instance == null) {
            instance = new ComputerDaoImpl();
        }
        return instance;
    }

    private ComputerDaoImpl() {

    }

    @Override
    public long insert(Computer entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getHddId(),
                entity.getHullId(),
                entity.getKeyboardId(),
                entity.getMonitorId(),
                entity.getMotherboardId(),
                entity.getMouseId(),
                entity.getOrderStatus().toString(),
                entity.getRamId(),
                entity.getSpeakerId(),
                entity.getPowerSupplyId(),
                entity.getUserId(),
                entity.getVideoCardId(),
                entity.getTotalCoast(),
                entity.getProcessorId()
        );
    }

    @Override
    public void update(Computer entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getName(),
                entity.getHddId(),
                entity.getHullId(),
                entity.getKeyboardId(),
                entity.getMonitorId(),
                entity.getMotherboardId(),
                entity.getMouseId(),
                entity.getOrderStatus().toString(),
                entity.getRamId(),
                entity.getSpeakerId(),
                entity.getPowerSupplyId(),
                entity.getUserId(),
                entity.getVideoCardId(),
                entity.getTotalCoast(),
                entity.getProcessorId()
        );

    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);
    }

    @Override
    public Optional<Computer> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().
                executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Computer> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor().
                executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectAll(int offset, int length) throws DaoException {
        return null;
    }

    @Override
    public long selectCountAll() throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByName(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectBySpeakerId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountBySpeakerId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByHddId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByHddId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByHullId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByHullId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMouseId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMouseId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMonitorId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMonitorId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByKeyboardId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByKeyboardId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByPowerSupplyId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByPowerSupplyId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByRamId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByRamId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMotherBoardId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMotherBoardId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByVideoCardId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByVideoCardId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByProcessorId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByProcessorId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByTotalCoast(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByTotalCoast(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByUserId(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByUserId(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByOrderStatus(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByOrderStatus(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByNameForUser(int offset, int length, String keyword, String userId) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByNameForUser(String keyword, String userId) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectAllForActiveOrder(int offset, int length) throws DaoException {
        return null;
    }

    @Override
    public long selectCountAllForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByNameForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByNameForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByOrderStatusForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByOrderStatusForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectBySpeakerIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountBySpeakerIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByHddIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByHddIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByHullIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByHullIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMouseIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMouseIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMonitorIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMonitorIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByKeyboardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByKeyboardIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByPowerSupplyIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByPowerSupplyIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByRamIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByRamIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByMotherBoardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByMotherBoardIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByVideoCardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByVideoCardIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByProcessorIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByProcessorIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByTotalCoastForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByTotalCoastForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByUserIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByUserIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<Computer> selectByIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByIdForActiveOrder(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public Computer buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return ComputerDao.super.buildEntityFromResultSet(resultSet);
    }
}
