package lk.ijse.reservate.dao;

import lk.ijse.reservate.dao.custom.impl.EmployeeDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS,QUERY_DAO
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
            case ITEM:
            case ORDER:
            case ORDER_DETAILS:
            case QUERY_DAO:
                return new EmployeeDAOImpl();
            default:
                return null;
        }
    }


}