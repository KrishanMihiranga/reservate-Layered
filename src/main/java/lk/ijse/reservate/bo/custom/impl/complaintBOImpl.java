package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.ComplaintBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.custom.ComplaintDAO;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.entity.Complaint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class complaintBOImpl implements ComplaintBO {

    ComplaintDAO complaintDAO = (ComplaintDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMPLAINT);
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return complaintDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    @Override
    public boolean add(ComplaintDTO entity) throws SQLException, ClassNotFoundException {
    return  complaintDAO.add(new Complaint(entity.getComplaintId(), entity.getDate(), entity.getTime(), entity.getGuestid(), entity.getMealorderid(), entity.getHallreservationid(), entity.getRoomreservationid(), entity.getDescription()));
    }

    @Override
    public boolean update(ComplaintDTO entity) throws SQLException, ClassNotFoundException {
        return complaintDAO.update(new Complaint( entity.getComplaintId(),entity.getDate(), entity.getTime(), entity.getGuestid(), entity.getMealorderid(), entity.getHallreservationid(), entity.getRoomreservationid(), entity.getDescription()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return complaintDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ComplaintDTO setFields(String id) throws SQLException, ClassNotFoundException {
       Complaint complaint = complaintDAO.setFields(id);
       return new ComplaintDTO(complaint.getComplaintID(), complaint.getDate(), complaint.getTime(), complaint.getGuestid(), complaint.getMealorderid(), complaint.getHallreservationid(), complaint.getRoomreservationid(), complaint.getDescription());

    }

    @Override
    public List<String> getRIds() throws SQLException {
        return complaintDAO.getRIds();
    }

    @Override
    public List<String> getGIds() throws SQLException {
      return complaintDAO.getGIds();
    }

    @Override
    public List<String> getHIds() throws SQLException {
       return complaintDAO.getHIds();
    }

    @Override
    public List<String> getMIds() throws SQLException {
       return complaintDAO.getMIds();
    }

    @Override
    public List<ComplaintDTO> getAll() throws SQLException {

        List<Complaint> allEntityData = complaintDAO.getAll();
        List<ComplaintDTO> data = new ArrayList<>();
        for (Complaint complaint : allEntityData) {
            data.add(new ComplaintDTO(complaint.getComplaintID(), complaint.getDate(), complaint.getTime(), complaint.getGuestid(), complaint.getMealorderid(), complaint.getHallreservationid(), complaint.getRoomreservationid(), complaint.getDescription()));
        }
        return data;
    }


}
