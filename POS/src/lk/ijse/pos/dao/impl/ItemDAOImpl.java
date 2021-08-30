package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.ItemDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.view.tblmodel.ItemTM;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean saveItem( Item item ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement( "INSERT INTO Item VALUES (?,?,?,?)");

        pstm.setObject(1, item.getCode ());
        pstm.setObject(2, item.getDescription ());
        pstm.setObject(3, item.getQtyOnHand ());
        pstm.setObject(4, item.getUnitPrice ());
        return (pstm.executeUpdate ()>0);
    }

    @Override
    public boolean updateItem( Item item ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setObject(1, item.getCode ());
        pstm.setObject(2, item.getDescription ());
        pstm.setObject(3, item.getUnitPrice ());
        pstm.setObject(4, item.getQtyOnHand ());
        return (pstm.executeUpdate ()>0);
    }

    @Override
    public boolean deleteItem( String code ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");

        pstm.setObject(1, code);
        return (pstm.executeUpdate ()>0);
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery( "SELECT * FROM Item");

        ArrayList<Item> alItems = new ArrayList<>();

        while (rst.next()){

            Item item = new Item(rst.getString(1),
                                     rst.getString(2),
                                     rst.getBigDecimal(3),
                                     rst.getInt(4));

            alItems.add(item);

        }
        return alItems;
    }

    @Override
    public Item searchItem(String code) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Item where code=?");
        stm.setObject(1, code);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Item(rst.getString(1),
                            rst.getString(2),
                            rst.getBigDecimal(3),
                            rst.getInt(4));
        }
        return null;
    }

    @Override
    public boolean updateItemQtyOnHand( String code,int qtyOnHand ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");

        pstm.setObject(1, qtyOnHand);
        pstm.setObject(2, code);
        return (pstm.executeUpdate ()>0);
    }

}
