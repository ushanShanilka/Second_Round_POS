package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.OrderDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Orders;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean addOrder(Orders orders) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Orders VALUES (?,?,?)";
        PreparedStatement pstm = connection.prepareStatement( sql);
        pstm.setObject(1, orders.getId ());
        pstm.setObject(2, orders.getDate ());
        pstm.setObject(3, orders.getCustomerId (  ));
        return (pstm.executeUpdate ()>0);
    }

    @Override
    public boolean deleteOrder(){
        throw new UnsupportedOperationException ( "Error!" );
    }

    @Override
    public boolean updateOrder(){
        throw new UnsupportedOperationException ( "Error!" );
    }

    @Override
    public Orders searchOrder(){
        throw new UnsupportedOperationException ( "Error!" );
    }

    @Override
    public ArrayList<Orders> getAllOrders(){
        return getAllOrders ();
    }
}