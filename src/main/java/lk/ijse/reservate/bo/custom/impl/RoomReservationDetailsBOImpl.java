package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomReservationDetailsBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomReservationDAO;
import lk.ijse.reservate.dao.custom.RoomReservationDetailsDAO;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.dto.RoomReservationDetailsDTO;
import lk.ijse.reservate.entity.Complaint;
import lk.ijse.reservate.entity.RoomReservationDetails;
import lk.ijse.reservate.entity.roomreservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDetailsBOImpl implements RoomReservationDetailsBO {

    RoomReservationDetailsDAO roomReservationDAO = (RoomReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATIONDETAILS);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.add(new RoomReservationDetails(entity.getRoomReservationId(),entity.getRoomNumber()));

    }

    @Override
    public boolean update(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservationDetailsDTO setFields(String id) throws SQLException, ClassNotFoundException {

        RoomReservationDetails r = roomReservationDAO.setFields(id);
        return new RoomReservationDetailsDTO(r.getRoomReservationId(), r.getRoomNumber());

    }

    @Override
    public List<RoomReservationDetailsDTO> getAll() throws SQLException {

        List<RoomReservationDetails> allEntityData = roomReservationDAO.getAll();
        List<RoomReservationDetailsDTO> data = new ArrayList<>();

        for (RoomReservationDetails r : allEntityData) {
            data.add(new RoomReservationDetailsDTO(r.getRoomReservationId(), r.getRoomNumber()));
        }
        return data;

    }

    @Override
    public boolean removeR(String roomReservationId) throws SQLException {
       return roomReservationDAO.removeR(roomReservationId);
    }

    @Override
    public String getRoom(String value) throws SQLException {
        return roomReservationDAO.getRoom(value);
    }


}
