package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.RoomReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationBO extends SuperBO {


    public  boolean isValid(String roomNumber) throws SQLException ;

    public RoomReservationDTO setRFields(String roomnumber) throws SQLException ;

    public  boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(RoomReservationDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(RoomReservationDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
