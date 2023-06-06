package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {
    public  double generateTotValue() throws SQLException ;
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  List<String> getGIds() throws SQLException ;

    public  List<String> getOIds() throws SQLException;

    public  List<String> getHIds() throws SQLException;

    public  List<String> getRds() throws SQLException;

    public  boolean save(String paymentId, String guestId, String mealOrderId, String hallReservationId, String roomReservationId, String date, String time, double amount) throws SQLException;

    public  boolean remove(String paymentId) throws SQLException ;

    public  PaymentDTO setFields(String paymentId) throws SQLException ;

}
