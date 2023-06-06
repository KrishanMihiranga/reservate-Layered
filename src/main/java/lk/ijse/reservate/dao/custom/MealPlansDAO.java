package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.MealPlansDTO;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  boolean save(String packageId, String mealPlan, String mealType, String description, String price) throws SQLException ;
    public  List<String> getIds() throws SQLException;

    public  boolean update(String packageId, String mealPlan, String mealType, String description, String price) throws SQLException ;

    public  boolean remove(String packageId) throws SQLException ;

    public  MealPlansDTO setFields(String packageId) throws SQLException ;

    public  List<MealPlansDTO> getAll() throws SQLException ;

    public  String getItems(String packageId) throws SQLException ;

}
