package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    /*Property  Injection*/
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance ( ).getDAO ( DAOFactory.DAOTypes.ITEM );

    @Override
    public boolean addItem ( ItemDTO item ) throws Exception {
        return itemDAO.add ( new Item ( item.getCode (),item.getDescription (),item.getUnitPrice (),item.getQtyOnHand () ) );
    }

    @Override
    public boolean deleteItem ( String code ) throws Exception {
        return itemDAO.delete ( code );
    }

    @Override
    public ItemDTO searchItem ( String code ) throws Exception {
        Item search = itemDAO.search ( code );
        return new ItemDTO ( search.getCode (),search.getDescription (),search.getUnitPrice (),search.getQtyOnHand () );
    }

    @Override
    public boolean updateItem ( ItemDTO item ) throws Exception {
        return itemDAO.update(new Item ( item.getCode (),item.getDescription (),item.getUnitPrice (),item.getQtyOnHand () ));
    }

    @Override
    public boolean updateItemQtyOnHand ( String code , int qtyOnHand ) throws Exception {
       return itemDAO.updateItemQtyOnHand ( code , qtyOnHand );
    }

    @Override
    public ArrayList< ItemDTO > getAllItem ( ) throws Exception {
        ArrayList< Item > all = itemDAO.getAll ( );

        ArrayList< ItemDTO > itemDTOS = new ArrayList<> ( );

        for ( Item item : all) {
            itemDTOS.add ( new ItemDTO ( item.getCode (), item.getDescription (),  item.getUnitPrice (), item.getQtyOnHand () ) );
        }
        return itemDTOS;
    }
}
