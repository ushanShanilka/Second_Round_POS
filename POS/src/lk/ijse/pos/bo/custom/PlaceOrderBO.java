package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO{
    public boolean purchaseOrder( OrdersDTO orders) throws Exception;

}
