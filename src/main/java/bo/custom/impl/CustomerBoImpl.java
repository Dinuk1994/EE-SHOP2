package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.util.DaoType;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.ITEM);


    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getAddress(),
                customerDto.getContactNumber(),
                customerDto.getEmail()

        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList=customerDao.getAll();
        List<CustomerDto> customerDtos=new ArrayList<>();

        for (Customer customer:entityList) {
            customerDtos.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getAddress(),
                    customer.getContactNumber(),
                    customer.getEmail()

            ));
        }
        return customerDtos;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getAddress(),
                customerDto.getContactNumber(),
                customerDto.getEmail()

        ));
    }


}
