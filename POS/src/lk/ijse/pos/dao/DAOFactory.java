package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if ( daoFactory==null ){
            daoFactory=new DAOFactory ();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,QUERY

    }

    public SuperDAO getDAO( DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl ();
            case ITEM:
                return new ItemDAOImpl ();
            case ORDER:
                return new OrderDAOImpl ();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl ();
            case QUERY:
                return new QueryDAOImpl ();
            default:
                return null;
        }
    }
}
