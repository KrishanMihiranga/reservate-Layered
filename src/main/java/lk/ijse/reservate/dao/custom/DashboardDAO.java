package lk.ijse.reservate.dao.custom;


import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dao.SuperDAO;

import java.sql.SQLException;

public interface DashboardDAO extends SuperDAO {

    public int getTotalRooms() throws SQLException;
   public int getTotalHalls() throws SQLException;
    public int getBookedHalls() throws SQLException;
    public int getBookedRooms() throws SQLException;
    public int getComplaints() throws SQLException;
}
