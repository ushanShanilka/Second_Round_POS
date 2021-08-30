package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.utils.CrudUtils;

import java.util.ArrayList;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean add( OrderDetails orderDetails ) throws Exception {
        return CrudUtils.execute ( "INSERT INTO OrderDetail VALUES (?,?,?,?)" ,orderDetails.getOrderId (),orderDetails.getItemCode (),orderDetails.getQty (),orderDetails.getUnitPrice ());
    }

    @Override
    public boolean update ( OrderDetails orderDetails ) throws Exception {
        return false;
    }

    @Override
    public boolean delete ( String s ) throws Exception {
        return false;
    }

    @Override
    public OrderDetails search ( String s ) throws Exception {
        return null;
    }

    @Override
    public ArrayList< OrderDetails > getAll ( ) throws Exception {
        return null;
    }

}
