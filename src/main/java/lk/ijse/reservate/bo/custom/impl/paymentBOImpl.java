package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.PaymentBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.PaymentDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class paymentBOImpl implements PaymentBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT paymentid FROM payment ORDER BY paymentid DESC LIMIT 1";
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
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(payment entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment (paymentid, guestid, MealOrderId, hallreservationid, roomreservationid, date, time, amount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getPaymentid(),entity.getGuestid(),entity.getMealOrderId(),entity.getHallreservationid(),entity.getRoomreservationid(),entity.getDate(),entity.getTime(),entity.getAmount());
    }

    @Override
    public boolean update(payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE paymentid = ?";
        return  SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public payment setFields(String id) throws SQLException, ClassNotFoundException {
         String sql = "SELECT * FROM payment WHERE paymentid = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String paymentid=resultSet.getString(1);
            String guestid=resultSet.getString(2);
            String MealOrderId=resultSet.getString(3);
            String hallreservationid=resultSet.getString(4);
            String roomreservationid=resultSet.getString(5);
            String date= (resultSet.getString(6));
            String time=resultSet.getString(7);
            String amount=resultSet.getString(8);
            return new payment(paymentid, guestid, MealOrderId, hallreservationid, roomreservationid, date, time, amount);
        }
        return null;
    }

    @Override
    public double generateTotValue() throws SQLException {
        double totalAmount = 0;
        String sql = "SELECT SUM(amount) as totalAmount FROM payment";
        ResultSet resultSet = SQLUtill.execute(sql);
        while(resultSet.next()){
            totalAmount =resultSet.getDouble("totalAmount");
        }
        return totalAmount;
    }

    @Override
    public List<String> getGIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> gIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            gIds.add(resultSet.getString(1));
        }
        return gIds;
    }

    @Override
    public List<String> getOIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT MealOrderId FROM mealorderdetails";
        List<String> oIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            oIds.add(resultSet.getString(1));
        }
        return oIds;
    }

    @Override
    public List<String> getHIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallReservationId FROM hallreservationdetails";
        List<String> hIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hIds.add(resultSet.getString(1));
        }
        return hIds;
    }

    @Override
    public List<String> getRds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomReservationId FROM roomreservationdetails";
        List<String> rIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            rIds.add(resultSet.getString(1));
        }
        return rIds;
    }


}
