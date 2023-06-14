package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    public  double generateTotValue() throws SQLException ;

    public  List<String> getGIds() throws SQLException ;

    public  List<String> getOIds() throws SQLException;

    public  List<String> getHIds() throws SQLException;

    public  List<String> getRds() throws SQLException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(PaymentDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(PaymentDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public PaymentDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
