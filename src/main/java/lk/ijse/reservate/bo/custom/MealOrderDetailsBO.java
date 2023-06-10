package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;

import java.sql.SQLException;

public interface MealOrderDetailsBO extends SuperBO {

    public  boolean add(String orderId, String packageId) throws SQLException ;
    public  boolean delete(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
