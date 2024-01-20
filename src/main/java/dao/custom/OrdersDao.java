package dao.custom;

import dao.CrudDao;
import dao.SuperDao;
import dto.OrdersDto;
import entity.Orders;

import java.sql.SQLException;

public interface OrdersDao extends SuperDao {
    boolean orderSaved(OrdersDto ordersDto) throws SQLException;
    OrdersDto lastOrder() throws SQLException, ClassNotFoundException;
}
