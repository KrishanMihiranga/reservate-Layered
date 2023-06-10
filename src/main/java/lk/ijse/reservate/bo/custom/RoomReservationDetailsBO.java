package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.RoomReservationDetailsDTO;
import lk.ijse.reservate.entity.RoomReservationDetails;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationDetailsBO extends CrudBO<RoomReservationDetailsDTO> {

    public  List<RoomReservationDetailsDTO> getAll() throws SQLException ;

    public  boolean removeR(String roomReservationId) throws SQLException ;

    public  String getRoom(String value) throws SQLException ;

}
