package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoomNumber FROM room ORDER BY RoomNumber DESC LIMIT 1";
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
            String newId = String.format("R%04d", newNum);
            return newId;
        }
        return "R0001";
    }

    @Override
    public boolean add(room entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO room(RoomNumber, RoomType, Price, Status) VALUES(?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getRoomNumber(),entity.getRoomType(),entity.getPrice(),entity.getStatus());
    }

    @Override
    public boolean update(room entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET RoomType = ?, Price = ?, Status = ? WHERE  RoomNumber = ?";
        return SQLUtill.execute(sql, entity.getRoomType(),entity.getPrice(),entity.getStatus(), entity.getRoomNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql= "DELETE FROM room WHERE RoomNumber = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomNumber FROM room";
        List<String> empIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            empIds.add(resultSet.getString(1));
        }
        return empIds;
    }

    @Override
    public room setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM room WHERE RoomNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String RoomNumber= resultSet.getString(1);
            String RoomType= resultSet.getString(2);
            Double Price= Double.valueOf(resultSet.getString(3));
            String Status= resultSet.getString(4);
            return new room(RoomNumber, RoomType, Price, Status);
        }
        return null;
    }


}
