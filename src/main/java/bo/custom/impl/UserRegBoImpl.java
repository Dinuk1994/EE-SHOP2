package bo.custom.impl;

import bo.custom.UserRegBo;
import dao.DaoFactory;
import dao.custom.UserRegDao;
import dao.util.DaoType;
import dto.UserDto;
import entity.User;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRegBoImpl implements UserRegBo {

    UserRegDao userRegDao= DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        if (isValidPassword(userDto.getPrimaryPassword())) {
            return userRegDao.save(new User(
                    userDto.getUserId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    userDto.getAddress(),
                    userDto.getContactNumber(),
                    userDto.getPrimaryPassword()
            ));
        } else {
            new Alert(Alert.AlertType.ERROR, "Password must contain 8 charactors and digits ");
            return false;
        }
    }


    @Override
    public boolean updateUser(UserDto userDto) {
        if (isValidPassword(userDto.getPrimaryPassword())) {
            try {
                return userRegDao.update(new User(
                        userDto.getUserId(),
                        userDto.getName(),
                        userDto.getEmail(),
                        userDto.getAddress(),
                        userDto.getContactNumber(),
                        userDto.getPrimaryPassword()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Password must contain 8 charactors and digits ");
            return false;
        }
    }

    @Override
    public boolean deleteUser(String Id) throws SQLException, ClassNotFoundException {
        return userRegDao.delete(Id);
    }

    @Override
    public List<UserDto> allUsers() throws SQLException, ClassNotFoundException {
        List<User> entityList=userRegDao.getAll();
        List<UserDto> dtoList=new ArrayList<>();
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

    //-------------------------Validations----------------------------------------------------------


    @Override
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
