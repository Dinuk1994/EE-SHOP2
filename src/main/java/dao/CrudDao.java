package dao;

import dto.UserDto;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends SuperDao {
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(User entity) throws SQLException, ClassNotFoundException;
    boolean delete(String entity) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
}
