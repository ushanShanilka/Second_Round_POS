package lk.ijse.pos.bo.custom;

import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.model.Orders;

import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO{
    public boolean purchaseOrder( Orders orders, ArrayList< OrderDetails > orderDetails ) throws Exception;

}
