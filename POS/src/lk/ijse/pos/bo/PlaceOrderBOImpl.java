package lk.ijse.pos.bo;

import com.sun.org.apache.xpath.internal.operations.Or;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.pos.model.Orders;

import java.util.ArrayList;

public class PlaceOrderBOImpl {


    /*Property  Injection*/
    private CustomerDAO customerDAO = new CustomerDAOImpl ( );

    private ItemDAO itemDAO = new ItemDAOImpl ( );

    private OrderDAO orderDAO = new OrderDAOImpl ( );

    private OrderDetailsDAO orderDetailsDAO =  new OrderDetailsDAOImpl ( );

    public boolean purchaseOrder( Orders orders, ArrayList<OrderDetailsDAO> orderDetails ) throws Exception {
        boolean add = orderDAO.add ( orders );
        return false;
    }
}
