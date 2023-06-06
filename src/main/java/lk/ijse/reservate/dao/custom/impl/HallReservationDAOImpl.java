package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.hallReservationDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HallReservationDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT HallReservationId FROM HallReservation ORDER BY HallReservationId DESC LIMIT 1";

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
            String newId = String.format("HR%04d", newNum);
            return newId;
        }
        return "HR0001";
    }

    public static boolean save(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException {
        String sql ="INSERT INTO HallReservation(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, checkIn, checkOut, hallReservationId, guestId, hallNumber);
    }

    public static boolean remove(String hallNumber) throws SQLException {
        String sql = "DELETE FROM HallReservation WHERE HallNumber = ?";
        return CrudUtil.execute(sql, hallNumber);
    }

    public static boolean isValid(String hallNumber) throws SQLException {
        String sql = "SELECT * FROM hallreservationdetails WHERE HallNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, hallNumber);
        if(resultSet.next()){
            return  true;
        }
        return false;
    }

    public static hallReservationDTO setFields(String id) throws SQLException {
        String sql = "SELECT * FROM HallReservation WHERE HallReservationId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String HallReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String HallNumber = resultSet.getString(5);

            return new hallReservationDTO(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
        }
        return null;
    }

    public static hallReservationDTO setHFields(String hallnumber) throws SQLException {

        String sql = "SELECT * FROM HallReservation WHERE HallNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, hallnumber);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String HallReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String HallNumber = resultSet.getString(5);

            return new hallReservationDTO(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
        }
        return null;

    }

    public static boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = HallReservationDAOImpl.save(checkIn, checkOut, hallReservationId, guestId, hallNumber);
            if(isSaved){
                boolean isAdded=  HallReservationDetailsDAOImpl.save(hallReservationId, hallNumber);
                if (isAdded){
                    con.commit();
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }
}
