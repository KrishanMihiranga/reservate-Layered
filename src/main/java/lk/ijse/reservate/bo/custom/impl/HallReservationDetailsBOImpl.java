package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallReservationDetailsBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallReservationDetailsDAO;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.dto.HallReservationDetailsDTO;
import lk.ijse.reservate.entity.Complaint;
import lk.ijse.reservate.entity.HallReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallReservationDetailsBOImpl implements HallReservationDetailsBO {

    HallReservationDetailsDAO dao = (HallReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATIONDETAILS);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(HallReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return dao.add(new HallReservationDetails(entity.getHallReservationId(), entity.getHallNumber()));

    }

    @Override
    public boolean update(HallReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return false;
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
    public HallReservationDetailsDTO setFields(String id) throws SQLException, ClassNotFoundException {

       HallReservationDetails hallReservationDetails = dao.setFields(id);
       return new HallReservationDetailsDTO(hallReservationDetails.getHallReservationId(), hallReservationDetails.getHallNumber());

    }

    @Override
    public List<HallReservationDetailsDTO> getAll() throws SQLException {

        List<HallReservationDetails> allEntityData = dao.getAll();
        List<HallReservationDetailsDTO> data = new ArrayList<>();
        for (HallReservationDetails hallReservationDetails : allEntityData) {
            data.add(new HallReservationDetailsDTO(hallReservationDetails.getHallReservationId(), hallReservationDetails.getHallNumber()));
        }
        return data;

    }

    @Override
    public boolean removeH(String hallReservationId) throws SQLException {
        return dao.removeH(hallReservationId);

    }

    @Override
    public String getHall(String value) throws SQLException {
       return dao.getHall(value);
    }


}
