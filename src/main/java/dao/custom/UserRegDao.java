package dao.custom;

import dao.CrudDao;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;

public interface UserRegDao extends CrudDao<User> {
    UserDto lastUser() throws SQLException, ClassNotFoundException;
}
