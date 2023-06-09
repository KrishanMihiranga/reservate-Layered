package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.ComplaintBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.ComplaintDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.entity.Complaint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class complaintBOImpl implements ComplaintBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ComplaintId FROM complaints ORDER BY ComplaintId DESC LIMIT 1";
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
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    @Override
    public boolean add(Complaint entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO complaints (ComplaintId, Date, time, guestid, mealorderid, hallreservationid, roomreservationid, description ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getComplaintID(), entity.getDate(), entity.getTime(), entity.getGuestid(), entity.getMealorderid(), entity.getHallreservationid(), entity.getRoomreservationid(), entity.getDescription());
    }

    @Override
    public boolean update(Complaint entity) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE complaints SET Date = ?, time = ?, guestid = ?, mealorderid = ?, hallreservationid = ?, roomreservationid = ?, description = ? WHERE ComplaintId = ?";
       return SQLUtill.execute(sql, entity.getDate(), entity.getTime(), entity.getGuestid(), entity.getMealorderid(), entity.getHallreservationid(), entity.getRoomreservationid(), entity.getDescription(), entity.getComplaintID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM complaints WHERE ComplaintId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Complaint setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM complaints WHERE ComplaintId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String ComplaintId=resultSet.getString(1);
            String Date=resultSet.getString(2);
            String time=resultSet.getString(3);
            String guestid=resultSet.getString(4);
            String mealorderid=resultSet.getString(5);
            String hallreservationid=resultSet.getString(6);
            String roomreservationid=resultSet.getString(7);
            String description=resultSet.getString(8);
            return new Complaint(ComplaintId, Date, time, guestid, mealorderid, hallreservationid, roomreservationid, description);
        }
        return null;
    }

    @Override
    public List<String> getRIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT RoomReservationId FROM roomreservationdetails";
        List<String> RIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            RIds.add(resultSet.getString(1));
        }
        return RIds;
    }

    @Override
    public List<String> getGIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> GIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            GIds.add(resultSet.getString(1));
        }
        return GIds;
    }

    @Override
    public List<String> getHIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT HallReservationId FROM hallreservationdetails";
        List<String> HIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            HIds.add(resultSet.getString(1));
        }
        return HIds;
    }

    @Override
    public List<String> getMIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT MealOrderId FROM mealorder";
        List<String> MIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            MIds.add(resultSet.getString(1));
        }
        return MIds;
    }

    @Override
    public List<ComplaintDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM complaints";
        List<ComplaintDTO> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
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
