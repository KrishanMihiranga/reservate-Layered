package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.roomreservation;

import java.sql.SQLException;

public interface RoomReservationBO extends CrudBO<roomreservation> {


    public  boolean isValid(String roomNumber) throws SQLException ;

    public roomreservation setRFields(String roomnumber) throws SQLException ;

    public  boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;

}
