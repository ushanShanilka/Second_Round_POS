package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.view.tblmodel.CustomerTM;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {
    public boolean addCustomer(Customer customer) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement( "INSERT INTO Customer VALUES (?,?,?,?)");

        pstm.setObject(1, customer.getcID ());
        pstm.setObject(2, customer.getName ());
        pstm.setObject(3, customer.getAddress ());
        pstm.setObject(4, 0);
        return true;
    }

    public boolean updateCustomer(Customer customer) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setObject(1, customer.getcID ());
        pstm.setObject(2, customer.getName ());
        pstm.setObject(3, customer.getAddress ());
        return true;
    }

    public boolean deleteCustomer(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setObject(1, id);
        return true;
    }

    public Customer searchCustomer(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery( "SELECT * FROM Customer where id=?");
        return null;
    }

    public ArrayList<Customer> getAllCustomer() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery( "SELECT * FROM Customer");
        ArrayList<Customer> alCustomers = new ArrayList<>();

        while (rst.next()) {

            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));

            alCustomers.add(customer);

        }
        return alCustomers;
    }
}
