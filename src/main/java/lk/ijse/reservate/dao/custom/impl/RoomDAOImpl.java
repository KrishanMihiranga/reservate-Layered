package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.RoomDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl {

    public static String generateNextRoomNumber() throws SQLException {
        String sql = "SELECT RoomNumber FROM room ORDER BY RoomNumber DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitRoomNumber(resultSet.getString(1));
        }
        return splitRoomNumber(null);
    }

    public static String splitRoomNumber(String currentOrderId) {
        if(currentOrderId != null) {
            int lastNum = Integer.parseInt(currentOrderId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("R%04d", newNum);
            return newId;
        }
        return "R0001";
    }

    public static boolean save(String roomNumber, String roomType, String price, String status) throws SQLException {
        String sql ="INSERT INTO room(RoomNumber, RoomType, Price, Status) VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql, roomNumber, roomType, price, status);
    }

    public static List<String> getIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomNumber FROM room";
        List<String> empIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            empIds.add(resultSet.getString(1));
        }
        return empIds;
    }

    public static boolean update(String roomNumber, String roomType, String valueOf, String status) throws SQLException {
        String sql = "UPDATE room SET RoomType = ?, Price = ?, Status = ? WHERE  RoomNumber = ?";
        return CrudUtil.execute(sql, roomType, valueOf, status, roomNumber);
    }

    public static boolean remove(String roomNumber) throws SQLException {
        String sql= "DELETE FROM room WHERE RoomNumber = ?";
        return CrudUtil.execute(sql, roomNumber);
    }

    public static RoomDTO setFields(String roomNumber) throws SQLException {

        String sql = "SELECT * FROM room WHERE RoomNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, roomNumber);

        if(resultSet.next()) {

            String RoomNumber= resultSet.getString(1);
            String RoomType= resultSet.getString(2);
            Double Price= Double.valueOf(resultSet.getString(3));
            String Status= resultSet.getString(4);


            return new RoomDTO(RoomNumber, RoomType, Price, Status);
        }
        return null;
    }
}
