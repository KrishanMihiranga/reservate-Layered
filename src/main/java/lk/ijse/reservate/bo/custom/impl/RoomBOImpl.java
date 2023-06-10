package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.RoomDTO;
import lk.ijse.reservate.entity.room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return roomDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
       return roomDAO.splitId(currentId);
    }

    @Override
    public boolean add(RoomDTO entity) throws SQLException, ClassNotFoundException {
        return roomDAO.add(new room(entity.getRoomNumber(),entity.getRoomType(),entity.getPrice(),entity.getStatus()));

    }

    @Override
    public boolean update(RoomDTO entity) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new room(entity.getRoomNumber(),entity.getRoomType(),entity.getPrice(),entity.getStatus()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
      return roomDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return roomDAO.getIds();
    }

    @Override
    public RoomDTO setFields(String id) throws SQLException, ClassNotFoundException {

        room room = roomDAO.setFields(id);
        return new RoomDTO(room.getRoomNumber(), room.getRoomType(), room.getPrice(), room.getStatus());

    }


}
