package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.HallReservationDetailsDTO;
import lk.ijse.reservate.entity.HallReservationDetails;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsBO extends CrudBO<HallReservationDetailsDTO> {

   public  List<HallReservationDetailsDTO> getAll() throws SQLException ;
    public  boolean removeH(String hallReservationId) throws SQLException ;
    public  String getHall(String value) throws SQLException ;
}
