package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.user;

import java.sql.SQLException;

public interface UserBO extends CrudBO<user> {

    public  boolean empCheck(String empId) throws SQLException ;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
}
