package lk.ijse.pos.bo;

import com.sun.org.apache.xpath.internal.operations.Or;
import lk.ijse.pos.controller.OrderFormController;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.model.Orders;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderBOImpl {


    /*Property  Injection*/
    private CustomerDAO customerDAO = new CustomerDAOImpl ( );

    private ItemDAO itemDAO = new ItemDAOImpl ( );

    private OrderDAO orderDAO = new OrderDAOImpl ( );

    private OrderDetailsDAO orderDetailsDAO =  new OrderDetailsDAOImpl ( );


    public boolean purchaseOrder( Orders orders, ArrayList< OrderDetails > orderDetails ) throws Exception {
        Connection connection = null;
        try {
            connection= DBConnection.getInstance ( ).getConnection ( );

        connection.setAutoCommit(false);
        boolean b1 = orderDAO.add ( orders );
        if (!b1) {
            connection.rollback();
            return false;
        }

            for ( OrderDetails orderDetail : orderDetails) {
                boolean add = orderDetailsDAO.add ( orderDetail );
                if ( !add ) {
                    connection.rollback ( );
                    return false;
                }


                int qtyOnHand = 0;

                Item item = itemDAO.search ( orderDetail.getItemCode ( ) );


                if ( item != null ) {
                    qtyOnHand = item.getQtyOnHand ( );
                }

                boolean b2 = itemDAO.updateItemQtyOnHand ( orderDetail.getItemCode ( ) , orderDetail.getQty ( ) );
                System.out.println ( b2 );

                if ( !b2 ) {
                    connection.rollback ( );
                    return false;
                }
            }

        connection.commit();

        } catch ( SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger( OrderFormController.class.getName()).log( Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace ( );
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
