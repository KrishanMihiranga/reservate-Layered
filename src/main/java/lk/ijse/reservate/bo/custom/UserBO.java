package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    public  boolean empCheck(String empId) throws SQLException ;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(UserDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(UserDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public UserDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
