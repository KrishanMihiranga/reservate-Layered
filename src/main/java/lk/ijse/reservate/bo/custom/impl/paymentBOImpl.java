package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.PaymentBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.PaymentDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.PaymentDTO;
import lk.ijse.reservate.entity.payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class paymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return paymentDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(PaymentDTO entity) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new payment(entity.getPaymentid(),entity.getGuestid(),entity.getMealOrderId(),entity.getHallreservationid(),entity.getRoomreservationid(),entity.getDate(),entity.getTime(),entity.getAmount()));

    }

    @Override
    public boolean update(PaymentDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PaymentDTO setFields(String id) throws SQLException, ClassNotFoundException {

        payment payment = paymentDAO.setFields(id);
        return new PaymentDTO(payment.getPaymentid(),payment.getGuestid(),payment.getMealOrderId(),payment.getHallreservationid(),payment.getRoomreservationid(),payment.getDate(),payment.getTime(),payment.getAmount());

    }

    @Override
    public double generateTotValue() throws SQLException {
        return paymentDAO.generateTotValue();
    }

    @Override
    public List<String> getGIds() throws SQLException {
       return paymentDAO.getGIds();
    }

    @Override
    public List<String> getOIds() throws SQLException {
        return paymentDAO.getOIds();
    }

    @Override
    public List<String> getHIds() throws SQLException {
        return paymentDAO.getHIds();
    }

    @Override
    public List<String> getRds() throws SQLException {
        return paymentDAO.getRds();
    }


}
