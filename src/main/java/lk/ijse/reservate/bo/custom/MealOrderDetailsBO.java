package lk.ijse.reservate.bo.custom;

import java.sql.SQLException;

public interface MealOrderDetailsBO {

    public  boolean add(String orderId, String packageId) throws SQLException ;
    public  boolean delete(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
