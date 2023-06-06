package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public  String generateNextUserId() throws SQLException ;

    public  String splitUserId(String currentOrderId) ;

    public  boolean save(String userId, String empId, String userName, String password) throws SQLException ;

    public  List<String> getIds() throws SQLException ;

    public  boolean update(String userId, String empId, String userName, String password) throws SQLException ;

    public  boolean remove(String userId) throws SQLException ;

    public  UserDTO setFields(String userId) throws SQLException ;

    public  boolean empCheck(String empId) throws SQLException ;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
}
