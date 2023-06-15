package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallMaintenanceDAO;
import lk.ijse.reservate.entity.hallmaintenance;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallMaintenanceDAOImpl implements HallMaintenanceDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT HallMaintenanceId FROM hallmaintenance ORDER BY HallMaintenanceId DESC LIMIT 1";
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
            String newId = String.format("HM%04d", newNum);
            return newId;
        }
        return "HM0001";
    }

    @Override
    public boolean add(hallmaintenance entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO hallmaintenance(HallMaintenanceId, HallNumber, Date, StartTime, EndTime)Values(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql,entity.getHallMaintenanceId(),entity.getHallNumber(),entity.getDate(),entity.getStartTime(),entity.getEndTime());
    }

    @Override
    public boolean update(hallmaintenance entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE hallmaintenance SET HallNumber = ?, Date = ?, StartTime = ?, EndTime = ? WHERE HallMaintenanceId = ?";
        return SQLUtill.execute(sql, entity.getHallMaintenanceId(),entity.getDate(),entity.getStartTime(),entity.getEndTime(),entity.getHallNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM hallmaintenance WHERE HallMaintenanceId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public hallmaintenance setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM hallmaintenance WHERE HallMaintenanceId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String HallMaintenanceId=resultSet.getString(1);
            Date date = Date.valueOf(resultSet.getString(2));
            String startTime=resultSet.getString(3);
            String EndTime=resultSet.getString(4);
            String hallNumber=resultSet.getString(5);
            return new hallmaintenance(HallMaintenanceId, date, startTime, EndTime, hallNumber);
        }
        return null;
    }

}
