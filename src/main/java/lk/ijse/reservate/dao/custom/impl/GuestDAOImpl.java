package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.GuestDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT GuestId FROM guest ORDER BY GuestId DESC LIMIT 1";

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
            String newId = String.format("G%04d", newNum);
            return newId;
        }
        return "G0001";
    }

    public static boolean save(String guestId, String userId, String nic, String fullname, String address, String mobile, String date, String email) throws SQLException {
        String sql ="INSERT INTO guest(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, guestId, userId, nic, fullname, address, mobile, date, email);
    }

    public static List<String> getIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT GuestId FROM guest";
        List<String> guestIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            guestIds.add(resultSet.getString(1));
        }
        return guestIds;
    }


    public static boolean update(String guestId, String userId, String nic, String fullname, String address, String mobile, String date, String email) throws SQLException {
        String sql = "UPDATE guest SET UserId = ?, Nic = ?, Fullname = ?, Address = ?, Mobile = ?, Date = ?, Email = ? WHERE GuestId = ?";
        return CrudUtil.execute(sql, userId, nic, fullname, address, mobile, date, email, guestId);
    }

    public static boolean remove(String guestId) throws SQLException {
        String sql = "DELETE FROM guest WHERE GuestId = ?";
        return CrudUtil.execute(sql, guestId);
    }

    public static GuestDTO setFields(String guestId) throws SQLException {

        String sql = "SELECT * FROM guest WHERE GuestId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, guestId);

        if(resultSet.next()) {
            String GuestId=resultSet.getString(1);
            String UserId=resultSet.getString(2);
            String Nic=resultSet.getString(3);
            String Fullname=resultSet.getString(4);
            String Address=resultSet.getString(5);
            String Mobile=resultSet.getString(6);
            String Date=resultSet.getString(7);
            String Email=resultSet.getString(8);

            return new GuestDTO(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
        }
        return null;
    }

    public static String getName(String value) throws SQLException {
        String name;
        String sql ="SELECT * FROM guest WHERE GuestId = ?";
        ResultSet resultSet =CrudUtil.execute(sql, value);
        if(resultSet.next()){
            name = resultSet.getString("Fullname");
        }else{
            name = "Nothing Found";
        }
        return name;
    }
}
