package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.OrderDAO;
import lk.ijse.pos.model.Orders;
import lk.ijse.pos.utils.CrudUtils;

import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean addOrder(Orders orders) throws Exception {
        return CrudUtils.execute ( "INSERT INTO Orders VALUES (?,?,?)",orders.getId (),orders.getDate (),orders.getCustomerId ());
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
