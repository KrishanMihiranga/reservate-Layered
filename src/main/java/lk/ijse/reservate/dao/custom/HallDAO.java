package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.HallDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;


    public  boolean save(String hallNumber, String hallType, String price, String hallStatus) throws SQLException ;

    public  List<String> getIds() throws SQLException;

    public  boolean update(String hallNumber, String hallType, String price, String hallStatus) throws SQLException ;

    public  boolean remove(String hallNumber) throws SQLException ;

    public  HallDTO setFields(String hallNumber) throws SQLException ;

}
