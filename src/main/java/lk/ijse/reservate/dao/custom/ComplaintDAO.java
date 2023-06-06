package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.ComplaintDTO;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintDAO {

    public  String generateNextId() throws SQLException;

    public  String splitId(String currentOrderId);

    public  List<String> getRIds() throws SQLException ;

    public  List<String> getGIds() throws SQLException ;

    public  List<String> getHIds() throws SQLException ;

    public  List<String> getMIds() throws SQLException ;

    public  boolean save(String complainid, String date, String time, String guestId, String mealId, String roomId, String hallId, String description) throws SQLException ;

    public  boolean update(String complainid, String date, String time, String guestId, String mealId, String roomId, String hallId, String description) throws SQLException ;

    public  boolean remove(String complainid) throws SQLException ;

    public  ComplaintDTO setFields(String complainid) throws SQLException ;

    public  List<ComplaintDTO> getAll() throws SQLException ;
}
