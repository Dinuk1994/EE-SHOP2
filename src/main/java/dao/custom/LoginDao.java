package dao.custom;

import dao.CrudDao;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface
LoginDao extends CrudDao<User> {
    public boolean searchUser(User entity) throws SQLException, ClassNotFoundException;
}
