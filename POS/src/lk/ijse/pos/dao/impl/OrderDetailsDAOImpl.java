package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.OrderDetailsDAO;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.utils.CrudUtils;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    public boolean addOrderDetails( OrderDetails orderDetails ) throws Exception {
        return CrudUtils.execute ( "INSERT INTO OrderDetail VALUES (?,?,?,?)" ,orderDetails.getOrderId (),orderDetails.getItemCode (),orderDetails.getQty (),orderDetails.getUnitPrice ());

    }
}
