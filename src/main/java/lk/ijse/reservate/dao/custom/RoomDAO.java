package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO {
    public  String generateNextRoomNumber() throws SQLException ;

    public  String splitRoomNumber(String currentOrderId) ;

    public  boolean save(String roomNumber, String roomType, String price, String status) throws SQLException ;

    public  List<String> getIds() throws SQLException;

    public  boolean update(String roomNumber, String roomType, String valueOf, String status) throws SQLException ;

    public  boolean remove(String roomNumber) throws SQLException ;

    public  RoomDTO setFields(String roomNumber) throws SQLException ;

}
