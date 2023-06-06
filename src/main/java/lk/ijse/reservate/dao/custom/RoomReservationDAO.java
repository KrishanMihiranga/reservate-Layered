package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.roomReservationDTO;

import java.sql.SQLException;

public interface RoomReservationDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;
    public  boolean save(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;

    public  boolean remove(String roomNumber) throws SQLException ;

    public  boolean isValid(String roomNumber) throws SQLException ;

    public  roomReservationDTO setFields(String rId) throws SQLException;

    public  roomReservationDTO setRFields(String roomnumber) throws SQLException ;

    public  boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;

}
