package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.HallReservationDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsDAO {
    public  List<HallReservationDetailsDTO> getAll() throws SQLException ;


    public  boolean save(String hallReservationId, String hallNumber) throws SQLException ;

    public  boolean remove(String hallNumber) throws SQLException ;

    public  boolean removeH(String hallReservationId) throws SQLException ;

    public  HallReservationDetailsDTO setFields(String hallnumber) throws SQLException ;

    public  String getHall(String value) throws SQLException ;
}
