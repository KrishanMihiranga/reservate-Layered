package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<user> {

    public  boolean empCheck(String empId) throws SQLException ;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
}
