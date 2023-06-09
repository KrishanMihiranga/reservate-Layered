package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.GuestBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.GuestDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.guest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBOImpl implements GuestBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT GuestId FROM guest ORDER BY GuestId DESC LIMIT 1";
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
            String newId = String.format("G%04d", newNum);
            return newId;
        }
        return "G0001";
    }

    @Override
    public boolean add(guest entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO guest(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getGuestId(),entity.getUserId(), entity.getNic(),entity.getFullname(),entity.getAddress(), entity.getMobile(), entity.getDate(), entity.getEmail());
    }

    @Override
    public boolean update(guest entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE guest SET UserId = ?, Nic = ?, Fullname = ?, Address = ?, Mobile = ?, Date = ?, Email = ? WHERE GuestId = ?";
        return SQLUtill.execute(sql, entity.getUserId(),entity.getNic(), entity.getFullname(), entity.getAddress(), entity.getMobile(), entity.getDate(), entity.getEmail(), entity.getGuestId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM guest WHERE GuestId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> guestIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            guestIds.add(resultSet.getString(1));
        }
        return guestIds;
    }

    @Override
    public guest setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM guest WHERE GuestId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String GuestId=resultSet.getString(1);
            String UserId=resultSet.getString(2);
            String Nic=resultSet.getString(3);
            String Fullname=resultSet.getString(4);
            String Address=resultSet.getString(5);
            String Mobile=resultSet.getString(6);
            String Date=resultSet.getString(7);
            String Email=resultSet.getString(8);
            return new guest(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
        }
        return null;
    }

    @Override
    public String getName(String value) throws SQLException {
        String name;
        String sql ="SELECT * FROM guest WHERE GuestId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if(resultSet.next()){
            name = resultSet.getString("Fullname");
        }else{
            name = "Nothing Found";
        }
        return name;
    }

}
