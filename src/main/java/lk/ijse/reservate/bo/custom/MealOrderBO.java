package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.MealOrderDTO;
import lk.ijse.reservate.entity.mealOrder;

import java.sql.SQLException;

public interface MealOrderBO extends CrudBO<MealOrderDTO> {

    public MealOrderDTO getFields(String id) throws SQLException ;

    public  String getQty(String value) throws SQLException ;

    public  boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException ;

}
