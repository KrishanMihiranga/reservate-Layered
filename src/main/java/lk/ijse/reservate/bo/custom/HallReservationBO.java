package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.HallReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationBO extends SuperBO {

    public  boolean isValid(String hallNumber) throws SQLException;

    public HallReservationDTO setHFields(String hallnumber) throws SQLException ;

    public  boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallReservationDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(HallReservationDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
