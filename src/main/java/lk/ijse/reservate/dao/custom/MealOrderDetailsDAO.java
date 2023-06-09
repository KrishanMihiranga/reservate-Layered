package lk.ijse.reservate.dao.custom;

import java.sql.SQLException;

public interface MealOrderDetailsDAO {

    public  boolean add(String orderId, String packageId) throws SQLException ;
    public  boolean delete(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
