package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.util.ArrayList;

public interface ItemBO extends SuperBO{
    public boolean addItem( ItemDTO item ) throws Exception ;
    public boolean deleteItem(String code) throws Exception ;
    public ItemDTO searchItem(String code) throws Exception ;
    public boolean updateItem(ItemDTO item) throws Exception ;
    public boolean updateItemQtyOnHand(String code,int qtyOnHand) throws Exception ;
    public ArrayList<ItemDTO> getAllItem() throws Exception ;
}
