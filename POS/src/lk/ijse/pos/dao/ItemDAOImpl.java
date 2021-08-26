package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.view.tblmodel.ItemTM;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl {
    public boolean saveItem( Item item ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement( "INSERT INTO Item VALUES (?,?,?,?)");

        pstm.setObject(1, item.getCode ());
        pstm.setObject(2, item.getDescription ());
        pstm.setObject(3, new BigDecimal ( item.getQtyOnHand ()));
        pstm.setObject(4, Integer.parseInt(item.getUnitPrice ()));
        return (pstm.executeUpdate ()>0);
    }

    public boolean updateItem( Item item ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setObject(1, item.getCode ());
        pstm.setObject(2, item.getDescription ());
        pstm.setObject(3, new BigDecimal ( item.getQtyOnHand ()));
        pstm.setObject(4, Integer.parseInt(item.getUnitPrice ()));
        return (pstm.executeUpdate ()>0);
    }

    public boolean deleteItem( String code ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");

        pstm.setObject(1, code);
        return (pstm.executeUpdate ()>0);
    }

    public ArrayList<Item> getAll() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery( "SELECT * FROM Item");

        ArrayList<Item> alItems = new ArrayList<>();

        while (rst.next()){

            ItemTM item = new ItemTM(rst.getString(1),
                                     rst.getString(2),
                                     rst.getBigDecimal(3),
                                     rst.getInt(4));

            alItems.add(item);

        }
        return alItems;
    }

}
