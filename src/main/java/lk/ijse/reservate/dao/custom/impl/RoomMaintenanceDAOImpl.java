package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dto.roomMaintenanceDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMaintenanceDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT RoomMaintenanceId FROM roommaintenance ORDER BY RoomMaintenanceId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public static String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            int lastNum = Integer.parseInt(currentOrderId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RM%04d", newNum);
            return newId;
        }
        return "RM0001";
    }

    public static boolean save(String MaintenanceId, String RoomNumber, String Date, String StartTime, String EndTime) throws SQLException {
        String sql ="INSERT INTO roommaintenance(RoomMaintenanceId, RoomNumber, Date, startTime, EndTime)Values(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, MaintenanceId, RoomNumber, Date, StartTime, EndTime);
    }

    public static boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
        String sql = "UPDATE roommaintenance SET RoomNumber = ?, Date = ?, startTime = ?, EndTime = ? WHERE RoomMaintenanceId = ?";
        return CrudUtil.execute(sql, roomNumber, date ,startTime ,endTime ,maintenanceId);
    }

    public static boolean remove(String maintenanceId) throws SQLException {
        String sql = "DELETE FROM roommaintenance WHERE RoomMaintenanceId = ?";
        return CrudUtil.execute(sql, maintenanceId);
    }

    public static roomMaintenanceDTO setFields(String maintenanceId) throws SQLException {
        String sql = "SELECT * FROM roommaintenance WHERE RoomMaintenanceId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, maintenanceId);
        if (resultSet.next()) {
            String RoomMaintenanceId=resultSet.getString(1);
            String Date=resultSet.getString(2);
            String startTime=resultSet.getString(3);
            String EndTime=resultSet.getString(4);
            String RoomNumber=resultSet.getString(5);
            return new roomMaintenanceDTO(RoomMaintenanceId, Date, startTime, EndTime, RoomNumber);
        }
        return null;
    }
}
