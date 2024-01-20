package dao;

import dao.custom.impl.*;
import dao.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return daoFactory!=null?daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USER:return (T)new UserRegDaoImpl();
            case LOGIN:return (T)new LoginDaoImpl();
            case UPDATE:return (T)new UserUpdateDaoImpl();
            case ITEM:return (T)new CustomerDaoImpl();
            case ORDER:return (T)new OrdersDaoImpl();
            case CUSTOMER:return (T)new CustomerDaoImpl();
            case ORDER_DETAILS:return (T)new OrderDetailsDaoImpl();

        }
        return null;
    }
}
