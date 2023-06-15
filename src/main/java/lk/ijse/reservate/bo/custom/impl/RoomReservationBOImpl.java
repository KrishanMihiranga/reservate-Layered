package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.RoomReservationBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomReservationDAO;
import lk.ijse.reservate.dao.custom.RoomReservationDetailsDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.RoomReservationDTO;
import lk.ijse.reservate.entity.RoomReservationDetails;
import lk.ijse.reservate.entity.roomreservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomReservationBOImpl implements RoomReservationBO {

   RoomReservationDAO roomReservationDAO = (RoomReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATION);
   RoomReservationDetailsDAO r = (RoomReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATIONDETAILS);


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return roomReservationDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RR%04d", newNum);
            return newId;
        }
        return "RR0001";
    }

    @Override
    public boolean add(RoomReservationDTO entity) throws SQLException, ClassNotFoundException {

        return roomReservationDAO.add(new roomreservation(entity.getCheckIn(), entity.getCheckOut(), entity.getRoomReservationId(),entity.getGuestId(), entity.getRoomNumber()));

    }

    @Override
    public boolean update(RoomReservationDTO entity) throws SQLException, ClassNotFoundException {
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
    public RoomReservationDTO setFields(String id) throws SQLException, ClassNotFoundException {

        roomreservation roomreservation = roomReservationDAO.setFields(id);
        return new RoomReservationDTO(roomreservation.getCheckIn(), roomreservation.getCheckOut(), roomreservation.getRoomReservationId(), roomreservation.getGuestId(), roomreservation.getRoomNumber());

    }

    @Override
    public boolean isValid(String roomNumber) throws SQLException {
        return roomReservationDAO.isValid(roomNumber);
    }

    @Override
    public RoomReservationDTO setRFields(String roomnumber) throws SQLException {

        roomreservation roomreservation = roomReservationDAO.setRFields(roomnumber);
        return new RoomReservationDTO(roomreservation.getCheckIn(), roomreservation.getCheckOut(), roomreservation.getRoomReservationId(), roomreservation.getGuestId(), roomreservation.getRoomNumber());

    }

    @Override
    public boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = roomReservationDAO.add(new roomreservation(checkIn, checkOut, roomReservationId, guestId, roomNumber));
            if(isSaved){
                boolean isAdded=  r.add(new RoomReservationDetails(roomReservationId, roomNumber));
                if (isAdded){
                    con.commit();
                    return true;
                }
            }
            return false;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

}
