package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.roommaintenance;

import java.sql.SQLException;

public interface RoomMaintenanceDAO extends CrudDAO<roommaintenance> {

    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException ;

}
