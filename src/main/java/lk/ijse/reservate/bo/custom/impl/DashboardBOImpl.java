package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.DashboardBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.custom.DashboardDAO;
import lk.ijse.reservate.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardBOImpl implements DashboardBO {
        DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DASHBOARD);
    @Override
    public int getTotalRooms() throws SQLException {
       return dashboardDAO.getTotalRooms();
    }

    @Override
    public int getTotalHalls() throws SQLException {
       return dashboardDAO.getTotalHalls();
    }

    @Override
    public int getBookedHalls() throws SQLException {
        return dashboardDAO.getTotalHalls();
    }

    @Override
    public int getBookedRooms() throws SQLException {
      return dashboardDAO.getBookedRooms();
    }

    @Override
    public int getComplaints() throws SQLException {
         return dashboardDAO.getComplaints();
    }
}
