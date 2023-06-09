package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomMaintenanceDAO;
import lk.ijse.reservate.entity.roommaintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceDAOImpl implements RoomMaintenanceDAO {

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoomMaintenanceId FROM roommaintenance ORDER BY RoomMaintenanceId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RM%04d", newNum);
            return newId;
        }
        return "RM0001";
    }

    @Override
    public boolean add(roommaintenance entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO roommaintenance(RoomMaintenanceId, RoomNumber, Date, startTime, EndTime)Values(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getRoomMaintenanceId(),entity.getRoomNumber(),entity.getDate(),entity.getStartTime(),entity.getEndTime());
    }

    @Override
    public boolean update(roommaintenance entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roommaintenance WHERE RoomMaintenanceId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public roommaintenance setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roommaintenance WHERE RoomMaintenanceId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String RoomMaintenanceId=resultSet.getString(1);
            String Date=resultSet.getString(2);
            String startTime=resultSet.getString(3);
            String EndTime=resultSet.getString(4);
            String RoomNumber=resultSet.getString(5);
            return new roommaintenance(RoomMaintenanceId, Date, startTime, EndTime, RoomNumber);
        }
        return null;
    }

    @Override
    public boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
        String sql = "UPDATE roommaintenance SET RoomNumber = ?, Date = ?, startTime = ?, EndTime = ? WHERE RoomMaintenanceId = ?";
        return SQLUtill.execute(sql, roomNumber, date ,startTime ,endTime ,maintenanceId);
    }


}
