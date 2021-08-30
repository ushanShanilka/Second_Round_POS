package lk.ijse.pos.utils;

import lk.ijse.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CrudUtils {
    public static PreparedStatement getPreparedStatement (String sql, Object...params) throws Exception {
        Connection connection = DBConnection.getInstance ( ).getConnection ( );
        PreparedStatement preparedStatement = connection.prepareStatement ( sql );
        for ( int i = 0; i < params.length; i++ ) {
            preparedStatement.setObject ( (i+1),params[i] );
        }
        return preparedStatement;
    }
    public static boolean executeUpdate(String sql , Object...params) throws Exception {
        return getPreparedStatement ( sql,params ).executeUpdate ()>0;
    }
    public static ResultSet executeQuery(String sql,Object...params ) throws Exception {
        return getPreparedStatement ( sql,params ).executeQuery ();
    }
}
