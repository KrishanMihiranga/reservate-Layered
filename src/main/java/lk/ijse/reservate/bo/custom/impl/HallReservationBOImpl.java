package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallReservationBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallReservationDAO;
import lk.ijse.reservate.dao.custom.impl.HallReservationDAOImpl;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.hallReservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallReservationBOImpl implements HallReservationBO {

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT HallReservationId FROM HallReservation ORDER BY HallReservationId DESC LIMIT 1";
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
            String newId = String.format("HR%04d", newNum);
            return newId;
        }
        return "HR0001";
    }

    @Override
    public boolean add(hallReservation entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO HallReservation(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber) VALUES(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getCheckIn(), entity.getCheckOut(),entity.getHallReservationId(),entity.getGuestId(),entity.getHallNumber());
    }

    @Override
    public boolean update(hallReservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM HallReservation WHERE HallNumber = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public hallReservation setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HallReservation WHERE HallReservationId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String HallReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String HallNumber = resultSet.getString(5);
            return new hallReservation(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
        }
        return null;
    }

    @Override
    public boolean isValid(String hallNumber) throws SQLException {
        String sql = "SELECT * FROM hallreservationdetails WHERE HallNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, hallNumber);
        if(resultSet.next()){
            return  true;
        }
        return false;
    }

    @Override
    public hallReservation setHFields(String hallnumber) throws SQLException {
        String sql = "SELECT * FROM HallReservation WHERE HallNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, hallnumber);
        if (resultSet.next()) {
            String CheckIn = resultSet.getString(1);
            String CheckOut = resultSet.getString(2);
            String HallReservationId = resultSet.getString(3);
            String GuestId = resultSet.getString(4);
            String HallNumber = resultSet.getString(5);
            return new hallReservation(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
        }
        return null;
    }

    @Override
    public boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = HallReservationDAOImpl.add(entity.getCheckIn(), entity.getCheckOut(),entity.getHallReservationId(),entity.getGuestId(),entity.getHallNumber(), entity.getHallNumber());
            if(isSaved){
                boolean isAdded=  HallReservationDetailsDAOImpl.save(entity.getHallReservationId(),entity.getHallNumber());
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
