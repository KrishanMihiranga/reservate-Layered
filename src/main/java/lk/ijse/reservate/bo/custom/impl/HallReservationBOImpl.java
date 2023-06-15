package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallReservationBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallReservationDAO;
import lk.ijse.reservate.dao.custom.HallReservationDetailsDAO;
import lk.ijse.reservate.dao.custom.RoomReservationDAO;
import lk.ijse.reservate.dao.custom.impl.HallReservationDAOImpl;
import lk.ijse.reservate.dao.custom.impl.HallReservationDetailsDAOImpl;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.HallReservationDTO;
import lk.ijse.reservate.dto.RoomReservationDTO;
import lk.ijse.reservate.entity.HallReservationDetails;
import lk.ijse.reservate.entity.hallReservation;
import lk.ijse.reservate.entity.roomreservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallReservationBOImpl implements HallReservationBO {

    HallReservationDAO hallReservationDAO = (HallReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATION);
    HallReservationDetailsDAO hallReservationDetailsDAO = (HallReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATIONDETAILS);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return hallReservationDAO.getNextId();

    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("HR%04d", newNum);
            return newId;
        }
        return "HR0001";
    }

    @Override
    public boolean add(HallReservationDTO entity) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.add(new hallReservation(entity.getCheckIn(), entity.getCheckOut(),entity.getHallReservationId(),entity.getGuestId(),entity.getHallNumber()));

    }

    @Override
    public boolean update(HallReservationDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException {

        hallReservation hallReservation = hallReservationDAO.setFields(id);
        return new HallReservationDTO(hallReservation.getCheckIn(),hallReservation.getCheckOut(),hallReservation.getHallReservationId(),hallReservation.getGuestId(),hallReservation.getHallNumber());

    }

    @Override
    public boolean isValid(String hallNumber) throws SQLException {
        return hallReservationDAO.isValid(hallNumber);
    }

    @Override
    public HallReservationDTO setHFields(String hallnumber) throws SQLException {

        hallReservation hallReservation = hallReservationDAO.setHFields(hallnumber);
        return new HallReservationDTO(hallReservation.getCheckIn(),hallReservation.getCheckOut(),hallReservation.getHallReservationId(),hallReservation.getGuestId(),hallReservation.getHallNumber());

    }

    @Override
    public boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException {

        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = hallReservationDAO.add(new hallReservation(checkIn, checkOut, hallReservationId, guestId, hallNumber));
            if(isSaved){
                boolean isAdded=  hallReservationDetailsDAO.add(new HallReservationDetails(hallReservationId, hallNumber));
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
