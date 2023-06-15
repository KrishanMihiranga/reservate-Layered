package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomMaintenanceBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomMaintenanceDAO;
import lk.ijse.reservate.dto.RoomMaintenanceDTO;
import lk.ijse.reservate.entity.roommaintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceBOImpl implements RoomMaintenanceBO {

    RoomMaintenanceDAO roomMaintenanceDAO = (RoomMaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMMAINTENANCE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return roomMaintenanceDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RM%04d", newNum);
            return newId;
        }
        return "RM0001";
    }

    @Override
    public boolean add(RoomMaintenanceDTO entity) throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.add(new roommaintenance(entity.getRoomMaintenanceId(),entity.getDate(),entity.getStartTime(),entity.getEndTime(),entity.getRoomNumber()));
    }

    @Override
    public boolean update(RoomMaintenanceDTO entity) throws SQLException, ClassNotFoundException {
       return roomMaintenanceDAO.update(new roommaintenance(entity.getRoomMaintenanceId(),entity.getDate(),entity.getStartTime(),entity.getEndTime(),entity.getRoomNumber()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomMaintenanceDTO setFields(String id) throws SQLException, ClassNotFoundException {

        roommaintenance roommaintenance = roomMaintenanceDAO.setFields(id);
        return new RoomMaintenanceDTO(roommaintenance.getRoomMaintenanceId(), roommaintenance.getDate(), roommaintenance.getStartTime(), roommaintenance.getEndTime(), roommaintenance.getRoomNumber());

    }

    @Override
    public boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
        return roomMaintenanceDAO.updateRoom(roomNumber, date ,startTime ,endTime ,maintenanceId);

    }


}
