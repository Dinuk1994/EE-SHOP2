package dao.custom;

import dao.SuperDao;
import dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao extends SuperDao {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
}
