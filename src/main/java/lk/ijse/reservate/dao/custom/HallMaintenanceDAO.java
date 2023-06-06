package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.hallMaintenanceDTO;

import java.sql.SQLException;

public interface HallMaintenanceDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;


    public  boolean save(String maintenanceId, String hallNumber, String date, String startTime, String endTime) throws SQLException ;

    public  boolean updateHall(String maintenanceId, String hallNumber, String date, String startTime, String endTime) throws SQLException ;

    public  boolean remove(String maintenanceId) throws SQLException ;

    public  hallMaintenanceDTO setFields(String maintenanceId) throws SQLException ;
}
