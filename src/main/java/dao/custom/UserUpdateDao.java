package dao.custom;

import dao.CrudDao;
import entity.User;

import java.sql.SQLException;

public interface UserUpdateDao extends CrudDao<User> {

    public boolean searchUser(User entity) throws SQLException, ClassNotFoundException;
}
