package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.MealOrderDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface MealOrderBO extends SuperBO {

    public MealOrderDTO getFields(String id) throws SQLException ;

    public  String getQty(String value) throws SQLException ;

    public  boolean Order(String orderId, String guestId, String packageId, Date date, String qty, String orderId1, String packageId1) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(MealOrderDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(MealOrderDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public MealOrderDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
