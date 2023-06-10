package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallMaintenanceBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallMaintenanceDAO;
import lk.ijse.reservate.dto.HallMaintenanceDTO;
import lk.ijse.reservate.entity.hallmaintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallMaintenanceBOImpl implements HallMaintenanceBO {

    HallMaintenanceDAO dao = (HallMaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLMAINTENANCE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return dao.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
       return dao.splitId(currentId);
    }

    @Override
    public boolean add(HallMaintenanceDTO entity) throws SQLException, ClassNotFoundException {
        return dao.add(new hallmaintenance(entity.getHallMaintenanceId(),entity.getHallNumber(),entity.getDate(),entity.getStartTime(),entity.getEndTime()));
    }

    @Override
    public boolean update(HallMaintenanceDTO entity) throws SQLException, ClassNotFoundException {
        return dao.update(new hallmaintenance(entity.getHallNumber(),entity.getDate(),entity.getStartTime(),entity.getEndTime(),entity.getHallMaintenanceId()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HallMaintenanceDTO setFields(String id) throws SQLException, ClassNotFoundException {
        hallmaintenance hall = dao.setFields(id);
        return new HallMaintenanceDTO(hall.getHallMaintenanceId(), hall.getDate(), hall.getStartTime(), hall.getEndTime(), hall.getHallNumber());

    }

}
