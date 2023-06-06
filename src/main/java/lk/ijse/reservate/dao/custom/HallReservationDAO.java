package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.hallReservationDTO;

import java.sql.SQLException;

public interface HallReservationDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  boolean save(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;

    public  boolean remove(String hallNumber) throws SQLException ;

    public  boolean isValid(String hallNumber) throws SQLException ;

    public  hallReservationDTO setFields(String id) throws SQLException ;

    public  hallReservationDTO setHFields(String hallnumber) throws SQLException ;

    public  boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;

}
