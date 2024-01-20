package bo.custom.impl;

import bo.custom.UserUpdateBo;
import dao.DaoFactory;
import dao.custom.UserUpdateDao;
import dao.util.DaoType;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUpdateBoImpl implements UserUpdateBo {
    UserUpdateDao userUpdateDao= DaoFactory.getInstance().getDao(DaoType.UPDATE);

    @Override
    public boolean isFound(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userUpdateDao.searchUser(new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getAddress(),
                userDto.getContactNumber(),
                userDto.getPrimaryPassword()
        ));
    }

    @Override
    public List<UserDto> allUsers() throws SQLException, ClassNotFoundException {
        List<User> entityList=userUpdateDao.getAll();
        List<UserDto>  dtoList=new ArrayList<>();
        for (User user:entityList) {
            dtoList.add(new UserDto(
                    user.getUserId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getContactNumber(),
                    user.getNewPassword()
            ));
        }
        return dtoList;
    }

    public boolean isUpdate(UserDto userDto)  {
        try {
            return userUpdateDao.update(new User(
                    userDto.getUserId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    userDto.getAddress(),
                    userDto.getContactNumber(),
                    userDto.getPrimaryPassword()
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





    public boolean isValidPassword(String password) {
        if (password==null||password.length()<8){
            return false;
        }
        boolean hasDigit=false;
        boolean hasLetter=false;
        boolean hasSymbol=false;

        for (char ch:password.toCharArray()) {
            if (Character.isDigit(ch)){
                hasDigit = true;
            }else if(Character.isLetter(ch)){
                hasLetter=true;
            } else if (!Character.isWhitespace(ch)) {
                hasSymbol=true;
            }
            if (hasDigit && hasLetter && hasSymbol){
                break;
            }

        }
        return hasDigit && hasLetter && hasSymbol;
    }


}
