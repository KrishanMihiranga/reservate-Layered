package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomReservationBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomReservationDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.roomreservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomReservationBOImpl implements RoomReservationBO {

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoomReservationId FROM RoomReservation ORDER BY RoomReservationId DESC LIMIT 1";
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
            String newId = String.format("RR%04d", newNum);
            return newId;
        }
        return "RR0001";
    }

    @Override
    public boolean add(roomreservation entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO RoomReservation(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber) VALUES(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getCheckIn(), entity.getCheckOut(), entity.getRoomReservationId(),entity.getGuestId(), entity.getRoomNumber());
    }

    @Override
    public boolean update(roomreservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roomreservation WHERE RoomReservationId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public roomreservation setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM RoomReservation WHERE RoomReservationId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String RoomReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String RoomNumber = resultSet.getString(5);
            return new roomreservation(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
        }
        return null;
    }

    @Override
    public boolean isValid(String roomNumber) throws SQLException {
        String sql = "SELECT * FROM roomreservationdetails WHERE RoomNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, roomNumber);
        if(resultSet.next()){
            return  true;
        }
        return false;
    }

    @Override
    public roomreservation setRFields(String roomnumber) throws SQLException {
        String sql = "SELECT * FROM RoomReservation WHERE RoomNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, roomnumber);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String RoomReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String RoomNumber = resultSet.getString(5);
            return new roomreservation(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
        }
        return null;
    }

    @Override
    public boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
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
