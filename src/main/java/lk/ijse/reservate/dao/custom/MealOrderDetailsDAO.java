package lk.ijse.reservate.dao.custom;

import java.sql.SQLException;

public interface MealOrderDetailsDAO {

    public  boolean save(String orderId, String packageId) throws SQLException ;
    public  boolean remove(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
