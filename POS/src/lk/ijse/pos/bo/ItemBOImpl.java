package lk.ijse.pos.bo;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;

import java.util.ArrayList;

public class ItemBOImpl {

    /*Property  Injection*/
    private ItemDAO itemDAO = new ItemDAOImpl ( );

    public boolean addItem( Item item ) throws Exception {
        return itemDAO.add ( item );
    }

    public boolean deleteItem(String code) throws Exception {
        return itemDAO.delete ( code );
    }
    public Item searchItem(String code) throws Exception {
        return itemDAO.search ( code );
    }
    public boolean updateItem(Item item) throws Exception {
        return itemDAO.update(item);
    }
    public boolean updateItemQtyOnHand(String code,int qtyOnHand) throws Exception {
        return itemDAO.updateItemQtyOnHand ( code,qtyOnHand );
    }
    public ArrayList<Item> getAllItem() throws Exception {
        return itemDAO.getAll ();
    }
}
