/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.model;

import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class Item {
    private String code;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;

    public Item ( ) {
    }

    public Item ( String code , String description , BigDecimal unitPrice , int qtyOnHand ) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode ( ) {
        return code;
    }

    public void setCode ( String code ) {
        this.code = code;
    }

    public String getDescription ( ) {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public BigDecimal getUnitPrice ( ) {
        return unitPrice;
    }

    public void setUnitPrice ( BigDecimal unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand ( ) {
        return qtyOnHand;
    }

    public void setQtyOnHand ( int qtyOnHand ) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString ( ) {
        return "Item{" +
               "code='" + code + '\'' +
               ", description='" + description + '\'' +
               ", unitPrice=" + unitPrice +
               ", qtyOnHand=" + qtyOnHand +
               '}';
    }
}
