package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.HallReservationDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsBO extends SuperBO {

   public  List<HallReservationDetailsDTO> getAll() throws SQLException ;
    public  boolean removeH(String hallReservationId) throws SQLException ;
    public  String getHall(String value) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallReservationDetailsDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(HallReservationDetailsDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallReservationDetailsDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
