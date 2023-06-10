package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.GuestBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.GuestDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.GuestDTO;
import lk.ijse.reservate.entity.guest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBOImpl implements GuestBO {
    GuestDAO guestDAO = (GuestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GUEST);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return guestDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
       return guestDAO.splitId(currentId);
    }

    @Override
    public boolean add(GuestDTO entity) throws SQLException, ClassNotFoundException {
        return guestDAO.add(new guest(entity.getGuestId(),entity.getUserId(), entity.getNic(),entity.getFullname(),entity.getAddress(), entity.getMobile(), entity.getDate(), entity.getEmail()));

    }

    @Override
    public boolean update(GuestDTO entity) throws SQLException, ClassNotFoundException {
        return guestDAO.update(new guest(entity.getUserId(),entity.getNic(), entity.getFullname(), entity.getAddress(), entity.getMobile(), entity.getDate(), entity.getEmail(), entity.getGuestId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return guestDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return guestDAO.getIds();
    }

    @Override
    public GuestDTO setFields(String id) throws SQLException, ClassNotFoundException {
        guest guest = guestDAO.setFields(id);
        return new GuestDTO(guest.getGuestId(), guest.getUserId(),guest.getNic(),guest.getFullname(),guest.getAddress(),guest.getMobile(),guest.getDate(),guest.getEmail());

    }

    @Override
    public String getName(String value) throws SQLException {
       return guestDAO.getName(value);
    }

}
