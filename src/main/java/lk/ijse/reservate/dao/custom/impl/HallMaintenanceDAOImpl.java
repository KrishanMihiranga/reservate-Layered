package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dto.hallMaintenanceDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallMaintenanceDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT HallMaintenanceId FROM hallmaintenance ORDER BY HallMaintenanceId DESC LIMIT 1";

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
            String newId = String.format("HM%04d", newNum);
            return newId;
        }
        return "HM0001";
    }


    public static boolean save(String maintenanceId, String hallNumber, String date, String startTime, String endTime) throws SQLException {
        String sql = "INSERT INTO hallmaintenance(HallMaintenanceId, HallNumber, Date, StartTime, EndTime)Values(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,maintenanceId,hallNumber,date,startTime,endTime);
    }

    public static boolean updateHall(String maintenanceId, String hallNumber, String date, String startTime, String endTime) throws SQLException {
        String sql = "UPDATE hallmaintenance SET HallNumber = ?, Date = ?, StartTime = ?, EndTime = ? WHERE HallMaintenanceId = ?";
        return CrudUtil.execute(sql, hallNumber,date,startTime,endTime,maintenanceId);
    }

    public static boolean remove(String maintenanceId) throws SQLException {
        String sql ="DELETE FROM hallmaintenance WHERE HallMaintenanceId = ?";
        return CrudUtil.execute(sql, maintenanceId);
    }

    public static hallMaintenanceDTO setFields(String maintenanceId) throws SQLException {
        String sql = "SELECT * FROM hallmaintenance WHERE HallMaintenanceId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, maintenanceId);
        if (resultSet.next()) {
            String HallMaintenanceId=resultSet.getString(1);
            String Date=resultSet.getString(2);
            String startTime=resultSet.getString(3);
            String EndTime=resultSet.getString(4);
            String hallNumber=resultSet.getString(5);
            return new hallMaintenanceDTO(HallMaintenanceId, Date, startTime, EndTime, hallNumber);
        }
        return null;
    }
}
