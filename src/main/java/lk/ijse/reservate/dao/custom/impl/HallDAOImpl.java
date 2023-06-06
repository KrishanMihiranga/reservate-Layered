package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.HallDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT HallNumber FROM hall ORDER BY HallNumber DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public static String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            int lastNum = Integer.parseInt(currentOrderId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("H%04d", newNum);
            return newId;
        }
        return "H0001";
    }


    public static boolean save(String hallNumber, String hallType, String price, String hallStatus) throws SQLException {
        String sql ="INSERT INTO hall(HallNumber, HallType, Price, Status) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, hallNumber, hallType, price, hallStatus);
    }

    public static List<String> getIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallNumber FROM hall";
        List<String> hallIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hallIds.add(resultSet.getString(1));
        }
        return hallIds;
    }

    public static boolean update(String hallNumber, String hallType, String price, String hallStatus) throws SQLException {
        String sql = "UPDATE hall SET HallType = ?, Price = ?, Status = ? WHERE HallNumber = ?";
        return CrudUtil.execute(sql, hallType, price, hallStatus, hallNumber);
    }

    public static boolean remove(String hallNumber) throws SQLException {
        String sql = "DELETE FROM hall WHERE HallNumber = ?";
        return CrudUtil.execute(sql, hallNumber);
    }

    public static HallDTO setFields(String hallNumber) throws SQLException {
        String sql = "SELECT * FROM hall WHERE HallNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, hallNumber);
            if(resultSet.next()){
                String HallNumber = resultSet.getString(1);
                String UserId= resultSet.getString(2);
                String HallType= resultSet.getString(3);
                Double Price=resultSet.getDouble(4);
                String Status= resultSet.getString(5);

                return new HallDTO(HallNumber, UserId, HallType, Price, Status);
            }
            return  null;
    }

}
