package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface CustomerDAO {
    public boolean addCustomer ( Customer customer ) throws Exception;

    public boolean updateCustomer ( Customer customer ) throws Exception;

    public boolean deleteCustomer ( String id ) throws Exception;

    public Customer searchCustomer ( String id ) throws Exception;

    public ArrayList< Customer > getAllCustomer ( ) throws Exception;

}
