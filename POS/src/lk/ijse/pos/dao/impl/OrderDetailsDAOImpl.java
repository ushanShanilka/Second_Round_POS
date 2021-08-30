package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.OrderDetailsDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.view.tblmodel.OrderDetailTM;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    public boolean addOrderDetails( OrderDetails orderDetails ) throws Exception {
        Connection connection = DBConnection.getInstance ( ).getConnection ( );
        PreparedStatement preparedStatement = connection.prepareStatement ( "INSERT INTO OrderDetail VALUES (?,?,?,?)" );
        preparedStatement.setObject ( 1 , orderDetails.getOrderId ( ) );
        preparedStatement.setObject ( 2 , orderDetails.getItemCode ( ) );
        preparedStatement.setObject ( 3 , orderDetails.getQty ( ) );
        preparedStatement.setObject ( 4 , orderDetails.getUnitPrice ( ) );
        return (preparedStatement.executeUpdate ( ) > 0);
    }
}
