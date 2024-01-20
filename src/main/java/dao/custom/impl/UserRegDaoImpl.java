package dao.custom.impl;

import dao.custom.LoginDao;
import dao.custom.UserRegDao;
import dao.util.CrudUtil;
import db.DBConnection;
import dto.UserDto;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRegDaoImpl implements UserRegDao {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO User VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(sql,entity.getUserId(),entity.getName(),entity.getEmail(),entity.getAddress(),entity.getContactNumber(),entity.getNewPassword());
    }


    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE User SET name=?, email=?, address=?, contactNumber=?, newPassword=? WHERE userId=?";
        return CrudUtil.execute(sql,entity.getName(),entity.getEmail(),entity.getAddress(),entity.getContactNumber(),entity.getNewPassword(),entity.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM User WHERE userId = ?";
        return CrudUtil.execute(sql,id);

    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> userList=new ArrayList<>();
        String sql="SELECT * FROM User";
        ResultSet resultset = CrudUtil.execute(sql);
        while (resultset.next()){
            userList.add(new User(
                    resultset.getString(1),
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getString(4),
                    resultset.getInt(5),
                    resultset.getString(6)
            ));

        }
        return userList;
    }

    @Override
    public UserDto lastUser() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM User ORDER BY UserID DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }
}
