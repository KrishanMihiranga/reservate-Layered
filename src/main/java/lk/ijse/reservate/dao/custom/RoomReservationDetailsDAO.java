package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.RoomReservationDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationDetailsDAO {
    public  List<RoomReservationDetailsDTO> getAll() throws SQLException ;

    public  boolean save(String roomReservationId, String roomNumber) throws SQLException ;

    public  boolean remove(String roomNumber) throws SQLException ;

    public  boolean removeR(String roomReservationId) throws SQLException ;

    public  RoomReservationDetailsDTO setFields(String roomnumber) throws SQLException ;

    public  String getRoom(String value) throws SQLException ;

}
