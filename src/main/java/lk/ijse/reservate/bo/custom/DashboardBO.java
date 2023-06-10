package lk.ijse.reservate.bo.custom;


import lk.ijse.reservate.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {

    public int getTotalRooms() throws SQLException;
   public int getTotalHalls() throws SQLException;
    public int getBookedHalls() throws SQLException;
    public int getBookedRooms() throws SQLException;
    public int getComplaints() throws SQLException;
}
