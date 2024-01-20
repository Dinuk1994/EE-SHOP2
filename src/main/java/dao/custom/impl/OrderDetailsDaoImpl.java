package dao.custom.impl;

import dao.custom.OrderDetailsDao;
import dao.util.CrudUtil;
import db.DBConnection;
import dto.OrderDetailsDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        boolean isDetailsSaved = false;
        for (OrderDetailsDto dto : list) {
            String sql = "INSERT INTO OrderDetails VALUES(?,?,?,?,?,?,?,?)";
            //int result = CrudUtil.execute(sql, dto.getStatus(), dto.getOrderId(), dto.getCustomerId());
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            pstm.setString(1,dto.getOrderId());
            pstm.setString(2, dto.getCustomerId());
            pstm.setString(3,dto.getCustomerName());
            pstm.setString(4,dto.getItemCategory());
            pstm.setString(5, dto.getItemName());
            pstm.setInt(6,dto.getItemQty());
            pstm.setDouble(7,dto.getItemPrice());
            pstm.setString(8,dto.getStatus());


            int result = pstm.executeUpdate();
            if (!(result>0)) {
                isDetailsSaved = false;
            }
        }
        return isDetailsSaved;
    }
}

