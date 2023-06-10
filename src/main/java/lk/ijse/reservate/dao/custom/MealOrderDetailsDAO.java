package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.SuperDAO;
import lk.ijse.reservate.entity.mealOrderDetails;

import java.sql.SQLException;

public interface MealOrderDetailsDAO extends SuperDAO {

    public  boolean add(String orderId, String packageId) throws SQLException ;
    public  boolean delete(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
