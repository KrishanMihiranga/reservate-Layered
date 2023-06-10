package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.RoomMaintenanceDTO;
import lk.ijse.reservate.entity.roommaintenance;

import java.sql.SQLException;

public interface RoomMaintenanceBO extends CrudBO<RoomMaintenanceDTO> {

    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException ;

}
