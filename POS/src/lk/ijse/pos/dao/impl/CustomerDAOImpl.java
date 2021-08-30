package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.CustomerDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;
import lk.ijse.pos.view.tblmodel.CustomerTM;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        return CrudUtils.executeUpdate ( "INSERT INTO Customer VALUES (?,?,?)",
                                              customer.getcID (),
                                              customer.getName (),
                                              customer.getAddress ());
    }

    @Override
    public boolean updateCustomer(Customer customer) throws Exception {
        return CrudUtils.executeUpdate ( "UPDATE Customer SET name=?, address=? WHERE id=?",
                                         customer.getcID (),
                                         customer.getName (),
                                         customer.getAddress ());
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return CrudUtils.executeUpdate ( "DELETE FROM Customer WHERE id=?",id );
    }

    @Override
    public Customer searchCustomer(String id) throws Exception {
        ResultSet resultSet = CrudUtils.executeQuery ( "SELECT * FROM Customer where id=?" );

        if (resultSet.next()) {
            return new Customer(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("address"));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws Exception {
        ResultSet resultSet = CrudUtils.executeQuery ( "SELECT * FROM Customer" );

        ArrayList<Customer> alCustomers = new ArrayList<>();

        while (resultSet.next()) {

            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));

            alCustomers.add(customer);

        }
        return alCustomers;
    }
}
