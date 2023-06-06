package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.GuestDTO;

import java.sql.SQLException;
import java.util.List;

public interface GuestDAO {
    public  String generateNextId() throws SQLException ;

    public  String splitId(String currentOrderId) ;

    public  boolean save(String guestId, String userId, String nic, String fullname, String address, String mobile, String date, String email) throws SQLException ;

    public  List<String> getIds() throws SQLException;

    public  boolean update(String guestId, String userId, String nic, String fullname, String address, String mobile, String date, String email) throws SQLException ;

    public  boolean remove(String guestId) throws SQLException ;

    public  GuestDTO setFields(String guestId) throws SQLException ;

    public  String getName(String value) throws SQLException ;
}
