package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.HallDTO;
import lk.ijse.reservate.entity.hall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallBOImpl implements HallBO {

    HallDAO hallDAO = (HallDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALL);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return hallDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return hallDAO.splitId(currentId);
    }

    @Override
    public boolean add(HallDTO entity) throws SQLException, ClassNotFoundException {
        return hallDAO.add(new hall(entity.getHallNumber(), entity.getUserId(), entity.getHallType(),entity.getPrice(),entity.getStatus()));
    }

    @Override
    public boolean update(HallDTO entity) throws SQLException, ClassNotFoundException {
        return hallDAO.update(new hall(entity.getHallNumber(), entity.getUserId(), entity.getHallType(),entity.getPrice(),entity.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return hallDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
      return hallDAO.getIds();
    }

    @Override
    public HallDTO setFields(String id) throws SQLException, ClassNotFoundException {
        hall hall = hallDAO.setFields(id);
        return new HallDTO(hall.getHallNumber(), hall.getUserId(), hall.getHallType(), hall.getPrice(), hall.getStatus());

    }


}
