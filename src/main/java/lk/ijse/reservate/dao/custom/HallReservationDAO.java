package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.hallReservation;

import java.sql.SQLException;

public interface HallReservationDAO extends CrudDAO<hallReservation> {

    public  boolean isValid(String hallNumber) throws SQLException;

    public hallReservation setHFields(String hallnumber) throws SQLException ;

   // public  boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;

}
