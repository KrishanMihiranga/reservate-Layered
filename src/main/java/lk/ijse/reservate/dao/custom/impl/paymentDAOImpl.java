package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.PaymentDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class paymentDAOImpl {
    public static double generateTotValue() throws SQLException {
        double totalAmount = 0;
        String sql = "SELECT SUM(amount) as totalAmount FROM payment";

        ResultSet resultSet = CrudUtil.execute(sql);
       while(resultSet.next()){
            totalAmount =resultSet.getDouble("totalAmount");
       }
        return totalAmount;
    }

    public static String generateNextId() throws SQLException {
        String sql = "SELECT paymentid FROM payment ORDER BY paymentid DESC LIMIT 1";

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
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    public static List<String> getGIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> gIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            gIds.add(resultSet.getString(1));
        }
        return gIds;
    }

    public static List<String> getOIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT MealOrderId FROM mealorderdetails";
        List<String> oIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            oIds.add(resultSet.getString(1));
        }
        return oIds;
    }

    public static List<String> getHIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallReservationId FROM hallreservationdetails";
        List<String> hIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hIds.add(resultSet.getString(1));
        }
        return hIds;
    }

    public static List<String> getRds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomReservationId FROM roomreservationdetails";
        List<String> rIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            rIds.add(resultSet.getString(1));
        }
        return rIds;
    }

    public static boolean save(String paymentId, String guestId, String mealOrderId, String hallReservationId, String roomReservationId, String date, String time, double amount) throws SQLException {
        String sql = "INSERT INTO payment (paymentid, guestid, MealOrderId, hallreservationid, roomreservationid, date, time, amount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, paymentId, guestId, mealOrderId, hallReservationId, roomReservationId, date, time, amount);
    }

    public static boolean remove(String paymentId) throws SQLException {
        String sql = "DELETE FROM payment WHERE paymentid = ?";
        return  CrudUtil.execute(sql, paymentId);
    }

    public static PaymentDTO setFields(String paymentId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE paymentid = ?";
        ResultSet resultSet = CrudUtil.execute(sql, paymentId);

        if(resultSet.next()) {
            String paymentid=resultSet.getString(1);
            String guestid=resultSet.getString(2);
            String MealOrderId=resultSet.getString(3);
            String hallreservationid=resultSet.getString(4);
            String roomreservationid=resultSet.getString(5);
            String date= (resultSet.getString(6));
            String time=resultSet.getString(7);
            String amount=resultSet.getString(8);


            return new PaymentDTO(paymentid, guestid, MealOrderId, hallreservationid, roomreservationid, date, time, amount);
        }
        return null;
    }
}
