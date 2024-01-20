package dao.custom.impl;

import dao.custom.LoginDao;
import dao.util.CrudUtil;
import db.DBConnection;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean searchUser(User entity) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM User WHERE email=? AND NewPassword=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,entity.getEmail());
        pstm.setString(2,entity.getNewPassword());

        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }


}
