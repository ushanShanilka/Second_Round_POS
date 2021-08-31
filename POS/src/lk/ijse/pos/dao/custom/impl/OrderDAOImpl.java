package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.utils.CrudUtils;

import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Orders orders) throws Exception {
        return CrudUtils.execute ( "INSERT INTO Orders VALUES (?,?,?)",orders.getId (),orders.getDate (),orders.getCustomerId ());
    }

    @Override
    public boolean update ( Orders orders ) throws Exception {
        throw new UnsupportedOperationException ( "Error!" );
    }

    @Override
    public boolean delete ( String s ) throws Exception {
        throw new UnsupportedOperationException ( "Error!" );
    }

    @Override
    public Orders search ( String s ) throws Exception {
        throw new UnsupportedOperationException ( "Error!" );
    }


    @Override
    public ArrayList<Orders> getAll(){
        return getAll ();
    }
}
