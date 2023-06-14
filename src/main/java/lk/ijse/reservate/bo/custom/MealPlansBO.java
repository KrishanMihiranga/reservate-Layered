package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.MealPlansDTO;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansBO extends SuperBO {


    public  List<MealPlansDTO> getAll() throws SQLException ;

    public  String getItems(String packageId) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(MealPlansDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(MealPlansDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public MealPlansDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
