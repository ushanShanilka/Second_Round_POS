package lk.ijse.pos.dto;

import java.math.BigDecimal;

public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;

    public OrderDetailsDTO ( ) {
    }

    public OrderDetailsDTO ( String orderId , String itemCode , int qty , BigDecimal unitPrice ) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId ( ) {
        return orderId;
    }

    public void setOrderId ( String orderId ) {
        this.orderId = orderId;
    }

    public String getItemCode ( ) {
        return itemCode;
    }

    public void setItemCode ( String itemCode ) {
        this.itemCode = itemCode;
    }

    public int getQty ( ) {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice ( ) {
        return unitPrice;
    }

    public void setUnitPrice ( BigDecimal unitPrice ) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString ( ) {
        return "OrderDetailsDTO{" +
               "orderId='" + orderId + '\'' +
               ", itemCode='" + itemCode + '\'' +
               ", qty=" + qty +
               ", unitPrice=" + unitPrice +
               '}';
    }
}
