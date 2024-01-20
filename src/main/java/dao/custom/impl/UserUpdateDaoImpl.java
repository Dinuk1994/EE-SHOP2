package dao.custom.impl;

import dao.custom.UserUpdateDao;
import dao.util.CrudUtil;
import db.DBConnection;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUpdateDaoImpl implements UserUpdateDao {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE User SET name=?, email=?, address=?, contactNumber=?, newPassword=? WHERE userId=?";
        return CrudUtil.execute(sql,entity.getName(),entity.getEmail(),entity.getAddress(),entity.getContactNumber(),entity.getNewPassword(),entity.getUserId());
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> userList=new ArrayList<>();
        String sql="SELECT * From User";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            userList.add(new User(
               resultSet.getString(1),
               resultSet.getString(2),
               resultSet.getString(3),
               resultSet.getString(4),
               resultSet.getInt(5),
               resultSet.getString(6)
            ));
        }
        return userList;
    }

    @Override
    public boolean searchUser(User entity) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM User WHERE email=? AND newPassword =?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,entity.getEmail());
        pstm.setString(2,entity.getNewPassword());

        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }
}
