package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.roomMaintenanceDTO;

import java.sql.SQLException;

public interface RoomMaintenanceDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  boolean save(String MaintenanceId, String RoomNumber, String Date, String StartTime, String EndTime) throws SQLException ;

    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException ;

    public  boolean remove(String maintenanceId) throws SQLException ;

    public  roomMaintenanceDTO setFields(String maintenanceId) throws SQLException ;
}
