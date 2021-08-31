package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.controller.OrderFormController;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    /*Property  Injection*/

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance ( ).getDAO ( DAOFactory.DAOTypes.ITEM );
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance ( ).getDAO ( DAOFactory.DAOTypes.ORDER );
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance ( ).getDAO ( DAOFactory.DAOTypes.ORDERDETAILS );

    @Override
    public boolean purchaseOrder( OrdersDTO orders ) throws Exception {

        Connection connection = null;
        try {
            connection= DBConnection.getInstance ( ).getConnection ( );

        connection.setAutoCommit(false);
        boolean b1 = orderDAO.add ( new Orders ( orders.getId (),orders.getDate (),orders.getCustomerId () ));
        if (!b1) {
            connection.rollback();
            return false;
        }

            for ( OrderDetailsDTO orderDetail : orders.getOrderDetails ()) {
                boolean add = orderDetailsDAO.add ( new OrderDetails ( orderDetail.getOrderId (),orderDetail.getItemCode (),orderDetail.getQty (),orderDetail.getUnitPrice () ) );
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
