package lk.ijse.pos.bo.custom;

import lk.ijse.pos.model.Item;

import java.util.ArrayList;

public interface ItemBO {
    public boolean addItem( Item item ) throws Exception ;
    public boolean deleteItem(String code) throws Exception ;
    public Item searchItem(String code) throws Exception ;
    public boolean updateItem(Item item) throws Exception ;
    public boolean updateItemQtyOnHand(String code,int qtyOnHand) throws Exception ;
    public ArrayList<Item> getAllItem() throws Exception ;
}
