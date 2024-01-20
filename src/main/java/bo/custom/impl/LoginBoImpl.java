package bo.custom.impl;

import bo.custom.LoginBo;
import dao.DaoFactory;
import dao.custom.LoginDao;
import dao.util.DaoType;
import dto.UserDto;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBoImpl implements LoginBo {

    LoginDao loginDao = DaoFactory.getInstance().getDao(DaoType.LOGIN);
    public boolean isFound(UserDto userDto) throws SQLException, ClassNotFoundException {
        return loginDao.searchUser(new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getAddress(),
                userDto.getContactNumber(),
                userDto.getPrimaryPassword()
        ));

    }
}
