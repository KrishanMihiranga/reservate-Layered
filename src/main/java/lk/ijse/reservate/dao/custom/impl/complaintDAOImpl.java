package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class complaintDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT ComplaintId FROM complaints ORDER BY ComplaintId DESC LIMIT 1";

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
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    public static List<String> getRIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomReservationId FROM roomreservationdetails";
        List<String> RIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            RIds.add(resultSet.getString(1));
        }
        return RIds;
    }

    public static List<String> getGIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> GIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            GIds.add(resultSet.getString(1));
        }
        return GIds;
    }

    public static List<String> getHIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallReservationId FROM hallreservationdetails";
        List<String> HIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            HIds.add(resultSet.getString(1));
        }
        return HIds;
    }

    public static List<String> getMIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT MealOrderId FROM mealorder";
        List<String> MIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            MIds.add(resultSet.getString(1));
        }
        return MIds;
    }

    public static boolean save(String complainid, String date, String time, String guestId, String mealId, String roomId, String hallId, String description) throws SQLException {
        String sql = "INSERT INTO complaints (ComplaintId, Date, time, guestid, mealorderid, hallreservationid, roomreservationid, description ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, complainid, date, time, guestId, mealId, hallId, roomId, description);
    }

    public static boolean update(String complainid, String date, String time, String guestId, String mealId, String roomId, String hallId, String description) throws SQLException {
        String sql ="UPDATE complaints SET Date = ?, time = ?, guestid = ?, mealorderid = ?, hallreservationid = ?, roomreservationid = ?, description = ? WHERE ComplaintId = ?";
        return CrudUtil.execute(sql, date, time, guestId, mealId, hallId, roomId, description, complainid);
    }

    public static boolean remove(String complainid) throws SQLException {
        String sql = "DELETE FROM complaints WHERE ComplaintId = ?";
        return CrudUtil.execute(sql, complainid);
    }

    public static ComplaintDTO setFields(String complainid) throws SQLException {
        String sql = "SELECT * FROM complaints WHERE ComplaintId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, complainid);

        if(resultSet.next()) {
            String ComplaintId=resultSet.getString(1);
            String Date=resultSet.getString(2);
            String time=resultSet.getString(3);
            String guestid=resultSet.getString(4);
            String mealorderid=resultSet.getString(5);
            String hallreservationid=resultSet.getString(6);
            String roomreservationid=resultSet.getString(7);
            String description=resultSet.getString(8);

            return new ComplaintDTO(ComplaintId, Date, time, guestid, mealorderid, hallreservationid, roomreservationid, description);
        }
        return null;
    }

    public static List<ComplaintDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM complaints";

        List<ComplaintDTO> data = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new ComplaintDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return data;
    }
}
