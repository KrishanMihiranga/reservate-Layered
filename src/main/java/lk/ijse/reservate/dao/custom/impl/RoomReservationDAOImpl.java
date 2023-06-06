package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.roomReservationDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomReservationDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT RoomReservationId FROM RoomReservation ORDER BY RoomReservationId DESC LIMIT 1";

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
            String newId = String.format("RR%04d", newNum);
            return newId;
        }
        return "RR0001";
    }
    public static boolean save(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
        String sql ="INSERT INTO RoomReservation(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, checkIn, checkOut, roomReservationId, guestId, roomNumber);
    }

    public static boolean remove(String roomNumber) throws SQLException {
        String sql = "DELETE FROM roomreservation WHERE RoomReservationId = ?";
        return CrudUtil.execute(sql, roomNumber);
    }

    public static boolean isValid(String roomNumber) throws SQLException {
        String sql = "SELECT * FROM roomreservationdetails WHERE RoomNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, roomNumber);
        if(resultSet.next()){
            return  true;
        }
        return false;
    }

    public static roomReservationDTO setFields(String rId) throws SQLException {
        String sql = "SELECT * FROM RoomReservation WHERE RoomReservationId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, rId);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String RoomReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String RoomNumber = resultSet.getString(5);

            return new roomReservationDTO(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
        }
        return null;
    }

    public static roomReservationDTO setRFields(String roomnumber) throws SQLException {
        String sql = "SELECT * FROM RoomReservation WHERE RoomNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, roomnumber);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String RoomReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String RoomNumber = resultSet.getString(5);

            return new roomReservationDTO(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
        }
        return null;
    }

    public static boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = RoomReservationDAOImpl.save(checkIn, checkOut, roomReservationId, guestId, roomNumber);
            if(isSaved){
                boolean isAdded=  RoomReservationDetailsDAOImpl.save(roomReservationId, roomNumber);
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
