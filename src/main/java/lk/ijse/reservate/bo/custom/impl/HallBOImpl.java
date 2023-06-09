package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.hall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallBOImpl implements HallBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT HallNumber FROM hall ORDER BY HallNumber DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("H%04d", newNum);
            return newId;
        }
        return "H0001";
    }

    @Override
    public boolean add(hall entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO hall(HallNumber, HallType, Price, Status) VALUES(?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getHallNumber(),entity.getHallType(),entity.getPrice(),entity.getStatus());
    }

    @Override
    public boolean update(hall entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE hall SET HallType = ?, Price = ?, Status = ? WHERE HallNumber = ?";
        return SQLUtill.execute(sql, entity.getHallType(), entity.getPrice(), entity.getStatus(), entity.getHallNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM hall WHERE HallNumber = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallNumber FROM hall";
        List<String> hallIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hallIds.add(resultSet.getString(1));
        }
        return hallIds;
    }

    @Override
    public hall setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM hall WHERE HallNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()){
            String HallNumber = resultSet.getString(1);
            String UserId= resultSet.getString(2);
            String HallType= resultSet.getString(3);
            Double Price=resultSet.getDouble(4);
            String Status= resultSet.getString(5);
            return new hall(HallNumber, UserId, HallType, Price, Status);
        }
        return  null;
    }


}
