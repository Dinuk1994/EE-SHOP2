package bo.custom;

import bo.SuperBo;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserRegBo extends SuperBo {
    boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto userDto) throws SQLException, ClassNotFoundException;

    boolean deleteUser(String userId) throws SQLException, ClassNotFoundException;

    boolean isValidPassword(String password);

    List<UserDto> allUsers() throws SQLException, ClassNotFoundException;
}
