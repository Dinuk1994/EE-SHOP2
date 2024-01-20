package bo.custom;

import bo.SuperBo;
import dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserUpdateBo extends SuperBo {

    public boolean isFound(UserDto userDto) throws SQLException, ClassNotFoundException;
    List<UserDto> allUsers() throws SQLException, ClassNotFoundException;
}
