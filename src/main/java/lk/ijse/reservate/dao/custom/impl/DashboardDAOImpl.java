package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardDAOImpl {
    public static int getTotalRooms() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS num_rooms FROM room");
        rs.next();
        int numRooms = rs.getInt("num_rooms");
        return numRooms;
    }

    public static int getTotalHalls() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS num_halls FROM hall");
        rs.next();
        int numhalls = rs.getInt("num_halls");
        return numhalls;
    }

    public static int getBookedHalls() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS booked_halls FROM hallreservationdetails");
        rs.next();
        int booked_hall = rs.getInt("booked_halls");
        return booked_hall;
    }

    public static int getBookedRooms() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS booked_rooms FROM roomreservationdetails");
        rs.next();
        int booked_room = rs.getInt("booked_rooms");
        return booked_room;
    }

    public static int getComplaints() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS com FROM complaints");
        rs.next();
        int complaints = rs.getInt("com");
        return complaints;
    }
}
