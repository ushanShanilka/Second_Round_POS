package lk.ijse.pos.bo.custom;

import lk.ijse.pos.controller.OrderFormController;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.model.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface PlaceOrderBO {
    public boolean purchaseOrder( Orders orders, ArrayList< OrderDetails > orderDetails ) throws Exception;

}
