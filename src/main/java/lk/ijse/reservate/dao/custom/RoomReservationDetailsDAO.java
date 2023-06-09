package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.RoomReservationDetails;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationDetailsDAO extends CrudDAO<RoomReservationDetails> {

    public  List<RoomReservationDetails> getAll() throws SQLException ;

    public  boolean removeR(String roomReservationId) throws SQLException ;

    public  String getRoom(String value) throws SQLException ;

}
