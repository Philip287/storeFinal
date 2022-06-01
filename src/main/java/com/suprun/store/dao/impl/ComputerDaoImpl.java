package com.suprun.store.dao.impl;

import com.suprun.store.dao.ComputerDao;
import com.suprun.store.entity.Computer;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComputerDaoImpl implements ComputerDao {

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
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT(computer_id)
            FROM computer
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_SPEAKER_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_speaker LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_SPEAKER_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_speaker LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_HDD_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_hdd LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_HDD_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_hdd LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_HULL_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_hull LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_HULL_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_hull LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    public static final String SELECT_MULTIPLE_BY_MOUSE_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_mouse LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_MOUSE_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_mouse LIKE CONCAT('%', '?', '?') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_MONITOR_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_monitor LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_MONITOR_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_monitor LIKE CONCAT('%', '?', '%') AND deleted <> 1;           
            """;

    private static final String SELECT_MULTIPLE_BY_KEYBOARD_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_keyboard LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_KEYBOARD_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_keyboard LIKE CONCAT('%', ?, '%') AND deleted <> 1; 
            """;

    private static final String SELECT_MULTIPLE_BY_POWER_SUPPLY_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_power_supply LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computers_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_POWER_SUPPLY_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_power_supply LIKE CONCAT('%', ?, '%') AND deleted <> 1;     
            """;

    private static final String SELECT_MULTIPLE_BY_RAM_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_ram LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_RAM_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_ram LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_MOTHERBOARD_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_motherboard LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_MOTHERBOARD_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_motherboard LIKE CONCAT('%', ?, '%') AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_VIDEO_CARD_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_video_card LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    public static final String SELECT_COUNT_BY_VIDEO_CARD_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_video_card LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PROCESSOR_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_processor LIKE CONCAT('%', ?, '?') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PROCESSOR_ID = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_processor LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_USER_ID = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_user LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_USER_ID = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_user LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;


    private static final String SELECT_MULTIPLE_BY_ORDER_STATUS = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE order_status LIKE ? AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_ORDER_STATUS = """
            SELECT COUNT (computers_id)
            FROM computers
            WHERE order_status LIKE ? AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_TOTAL_COST = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE total_cost LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER_BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_TOTAL_COST = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE total_cost LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME_FOR_USER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE name LIKE CONCAT('%', ?, '%') AND id_user = ? AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME_FOR_USER = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE name LIKE CONCAT('%', ?, '%') AND id_user = ? AND deleted <> 1;
            """;

    private static final String SELECT_ALL_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE deleted <> 1 AND order_status <> 'COMPLETED'
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL_FOR_ACTIVE_ORDER = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE deleted <> 1 AND order_status <> 'COMPLETED';
            """;

    private static final String SELECT_MULTIPLE_BY_NAME_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1 AND order_status <> 'COMPLETED'
            ORDER BY computer_id
            LIMIT ?,?;
            """;

    private static final String SELECT_COUNT_BY_NAME_FOR_ACTIVE_ORDER = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1 AND order_status <> 'COMPLETED';
            """;

    private static final String SELECT_MULTIPLE_BY_SPEAKER_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers
            WHERE id_speaker LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_SPEAKER_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT (computer_id)
            FROM computers
            WHERE id_speaker LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_HDD_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_hdd LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_HDD_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_hdd LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_HULL_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_hull LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_HULL_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_hull LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_MOUSE_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_mouse LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_MOUSE_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_mouse LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_MONITOR_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_monitor LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_MONITOR_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_monitor LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_KEYBOARD_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_keyboard LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_KEYBOARD_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_keyboard LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_POWER_SUPPLY_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_power_supply LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_POWER_SUPPLY_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_power_supply LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_RAM_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_ram LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_RAM_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_ram LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_MOTHERBOARD_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_motherboard LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_MOTHERBOARD_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_motherboard LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_VIDEO_CARD_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_video_card LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_VIDEO_CARD_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_video_card LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_PROCESSOR_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE id_processor LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PROCESSOR_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE id_processor LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_TOTAL_COST_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE total_cost LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_TOTAL_COST_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE total_cost LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
            """;

    private static final String SELECT_MULTIPLE_BY_USER_ID_FOR_ACTIVE_ORDER = """
            SELECT computer_id, id_user, total_coast, order_status, id_processor, id_video_card, id_motherboard, id_ram,
            id_power_supply, id_keyboard, id_monitor, id_mouse, id_hull, id_hdd, id_speaker, name
            FROM computers 
            WHERE user_id LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1
            ORDER BY computer_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_USER_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(computer_id)
            FROM computers
            WHERE user_id LIKE CONCAT('%', ?, '%') AND order_status <> 'COMPLETED' AND deleted <> 1;            
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
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, this, keyword);
    }

    @Override
    public List<Computer> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Computer> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor().
                executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<Computer> selectBySpeakerId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor().
                executeSelectMultiple(SELECT_MULTIPLE_BY_SPEAKER_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountBySpeakerId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_SPEAKER_ID, keyword);
    }

    @Override
    public List<Computer> selectByHddId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_HDD_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByHddId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_HDD_ID, keyword);
    }

    @Override
    public List<Computer> selectByHullId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_HULL_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByHullId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_HULL_ID, keyword);
    }

    @Override
    public List<Computer> selectByMouseId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MOUSE_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMouseId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MOUSE_ID, keyword);
    }

    @Override
    public List<Computer> selectByMonitorId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MONITOR_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMonitorId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MONITOR_ID, keyword);
    }

    @Override
    public List<Computer> selectByKeyboardId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_KEYBOARD_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByKeyboardId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_KEYBOARD_ID, keyword);
    }

    @Override
    public List<Computer> selectByPowerSupplyId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_POWER_SUPPLY_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPowerSupplyId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_POWER_SUPPLY_ID, keyword);
    }

    @Override
    public List<Computer> selectByRamId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_RAM_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByRamId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_RAM_ID, keyword);
    }

    @Override
    public List<Computer> selectByMotherBoardId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MOTHERBOARD_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMotherBoardId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MOTHERBOARD_ID, keyword);
    }

    @Override
    public List<Computer> selectByVideoCardId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_VIDEO_CARD_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByVideoCardId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_VIDEO_CARD_ID, keyword);
    }

    @Override
    public List<Computer> selectByProcessorId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PROCESSOR_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByProcessorId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PROCESSOR_ID, keyword);
    }

    @Override
    public List<Computer> selectByTotalCoast(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_TOTAL_COST, this, keyword, offset, length);
    }

    @Override
    public long selectCountByTotalCoast(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_TOTAL_COST, keyword);
    }

    @Override
    public List<Computer> selectByUserId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_USER_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByUserId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_USER_ID, keyword);
    }

    @Override
    public List<Computer> selectByOrderStatus(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ORDER_STATUS, this, keyword, offset, length);
    }

    @Override
    public long selectCountByOrderStatus(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ORDER_STATUS, keyword);
    }

    @Override
    public List<Computer> selectByNameForUser(int offset, int length, String keyword, String userId) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME_FOR_USER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByNameForUser(String keyword, String userId) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME_FOR_USER, keyword);
    }

    @Override
    public List<Computer> selectAllForActiveOrder(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_ALL_FOR_ACTIVE_ORDER, this, offset, length);
    }

    @Override
    public long selectCountAllForActiveOrder() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL_FOR_ACTIVE_ORDER);
    }

    @Override
    public List<Computer> selectByNameForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByNameForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectBySpeakerIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_SPEAKER_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountBySpeakerIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_SPEAKER_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByHddIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_HDD_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByHddIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_HDD_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByHullIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_HULL_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByHullIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_HULL_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByMouseIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MOUSE_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMouseIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MOUSE_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByMonitorIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MONITOR_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMonitorIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MONITOR_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByKeyboardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_KEYBOARD_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByKeyboardIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_KEYBOARD_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByPowerSupplyIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_POWER_SUPPLY_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPowerSupplyIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_POWER_SUPPLY_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByRamIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_RAM_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByRamIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_RAM_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByMotherBoardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_MOTHERBOARD_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByMotherBoardIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_MOTHERBOARD_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByVideoCardIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_VIDEO_CARD_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByVideoCardIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_VIDEO_CARD_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByProcessorIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PROCESSOR_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByProcessorIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PROCESSOR_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByTotalCoastForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_TOTAL_COST_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByTotalCoastForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_TOTAL_COST_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Computer> selectByUserIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_USER_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByUserIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_USER_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public Computer buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return ComputerDao.super.buildEntityFromResultSet(resultSet);
    }
}
