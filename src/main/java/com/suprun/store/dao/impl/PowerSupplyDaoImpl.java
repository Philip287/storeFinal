package com.suprun.store.dao.impl;

import com.suprun.store.dao.PowerSupplyDao;
import com.suprun.store.entity.PowerSupply;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PowerSupplyDaoImpl implements PowerSupplyDao {

    private static PowerSupplyDao instance;

    private static final String INSERT = """
            INSERT INTO powersupplys(name, price, picture_path, description)
            VALUES(?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE powersupplys
            SET name = ?, price ?, picture_path ?, description ?
            WHERE power_supply_id = ?;
            """;

    private static final String DELETE = """
            UPDATE powersupplys
            SET deleted = 1
            WHERE power_supply_id = ?;
            """;

    private static final String SELECT_ALL = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE deleted <> 1;
            """;

    private static final String SELECT_BY_ID = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE power_supply_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE power_supply_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE power_supply_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT name, price, picture_path, description
            FROM powersupplys
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY power_supply_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT (power_supply_id)
            FROM powersupplys
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private PowerSupplyDaoImpl() {

    }

    public static PowerSupplyDao getInstance() {
        if (instance == null) {
            instance = new PowerSupplyDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(PowerSupply entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void update(PowerSupply entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);
    }

    @Override
    public Optional<PowerSupply> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<PowerSupply> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<PowerSupply> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<PowerSupply> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<PowerSupply> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<PowerSupply> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<PowerSupply> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH);
    }

    @Override
    public PowerSupply buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return PowerSupplyDao.super.buildEntityFromResultSet(resultSet);
    }

}
