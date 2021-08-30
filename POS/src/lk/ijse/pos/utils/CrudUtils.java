package lk.ijse.pos.utils;

import lk.ijse.pos.db.DBConnection;
import java.sql.PreparedStatement;

public class CrudUtils {
    public static  <T>T execute(String sql,Object...params) throws Exception {
        PreparedStatement preparedStatement = DBConnection.getInstance ( ).getConnection ( ).prepareStatement ( sql );
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject ( (i+1),params[i] );
        }
        if ( sql.startsWith ( ("SELECT") )){
            //execute Query
            return (T)preparedStatement.executeQuery ();
        }else {
            //Execute UpDate
            return (T)(Boolean)(preparedStatement.executeUpdate ()>0);
        }
    }
}
