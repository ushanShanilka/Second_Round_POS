package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public interface OrderDAO {

    public boolean addOrder( Orders orders) throws Exception ;

    public boolean deleteOrder();

    public boolean updateOrder();

    public Orders searchOrder();

    public ArrayList<Orders> getAllOrders();
}
