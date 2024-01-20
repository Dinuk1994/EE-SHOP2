package dao.custom.impl;


import dao.DaoFactory;
import dao.custom.OrderDetailsDao;
import dao.custom.OrdersDao;

import dao.util.CrudUtil;
import dao.util.DaoType;
import db.DBConnection;
import dto.OrdersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDaoImpl implements OrdersDao {
    OrderDetailsDao orderDetailsDao= DaoFactory.getInstance().getDao(DaoType.ORDER_DETAILS);
    @Override
    public boolean orderSaved(OrdersDto ordersDto) throws SQLException {
        Connection connection=null;
        try {
            connection=DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql="INSERT INTO Orders VALUES(?,?,?)";
            //int resultSet = CrudUtil.execute(sql, ordersDto.getOrderId(), ordersDto.getItemCategory(), ordersDto.getItemName(), ordersDto.getItemQty(), ordersDto.getItemPrice(), ordersDto.getOrderDate(), ordersDto.getCustomerId());
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,ordersDto.getOrderId());
            pstm.setString(2,ordersDto.getOrderDate());
            pstm.setString(3,ordersDto.getCustomerId());

            int result = pstm.executeUpdate();

            if (result>0){
                boolean isSaved=orderDetailsDao.saveOrderDetails(ordersDto.getList());
                if (isSaved){
                    connection.commit();
                    return true;
                }
            }
        }catch (SQLException | ClassNotFoundException ex){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }



    @Override
    public OrdersDto lastOrder() throws SQLException, ClassNotFoundException {
       String sql="SELECT * FROM Orders ORDER BY OrderID DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrdersDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    null
            );
        }
        return null;
    }
}
