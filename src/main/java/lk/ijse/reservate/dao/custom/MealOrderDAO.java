package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.mealOrderDTO;
import lk.ijse.reservate.dto.selectMealDTO;

import java.sql.SQLException;

public interface MealOrderDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  boolean save(String orderId, String guestId, String packageId, String date, String qty) throws SQLException ;

    public  boolean update(String orderId, String guestId, String packageId, String date, String qty) throws SQLException ;

    public  boolean remove(String orderId) throws SQLException ;

    public  selectMealDTO setFields(String id) throws SQLException ;

    public  mealOrderDTO getFields(String id) throws SQLException ;

    public  String getQty(String value) throws SQLException ;

    public  boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException ;

}
