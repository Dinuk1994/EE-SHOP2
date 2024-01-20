package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    boolean update(Customer entity) throws SQLException, ClassNotFoundException;

    CustomerDto lastItem() throws SQLException, ClassNotFoundException;
}
