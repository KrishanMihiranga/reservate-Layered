package lk.ijse.reservate.dao.custom;

import java.sql.SQLException;

public interface DashboardDAO {
    public int getTotalRooms() throws SQLException;

    public int getTotalHalls() throws SQLException;

    public int getBookedHalls() throws SQLException;

    public int getBookedRooms() throws SQLException;

    public int getComplaints() throws SQLException;
}