package lk.ijse.reservate.dao.custom.impl;
import lk.ijse.reservate.dao.custom.UserDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT UserId FROM user ORDER BY UserId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("U%04d", newNum);
            return newId;
        }
        return "U0001";
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO user(UserID, EmpId, UserName, Password) VALUES(?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getUserId(), entity.getEmpId(), entity.getUserName(),entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        String sql= "UPDATE user SET EmpId = ?, UserName = ?, Password = ? WHERE UserID = ?";
        return SQLUtill.execute(sql, entity.getEmpId(),entity.getUserName(),entity.getPassword(),entity.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM user WHERE UserID = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT UserId FROM user";
        List<String> UserIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            UserIds.add(resultSet.getString(1));
        }
        return UserIds;
    }

    @Override
    public User setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE UserID = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String UserId= resultSet.getString(1);
            String EmpId= resultSet.getString(2);
            String UserName= resultSet.getString(3);
            String Password= resultSet.getString(4);
            return new User(UserId, EmpId, UserName, Password);
        }
        return null;
    }

    @Override
    public boolean empCheck(String empId) throws SQLException {
        String sql = "SELECT * FROM USER WHERE EmpId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, empId);
        if (resultSet.next()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean elegibleCheck(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserName = ?";
        ResultSet resultSet = SQLUtill.execute(sql, userName);
        if (resultSet.next()){
            String userN = resultSet.getString("UserName");
            String pass = resultSet.getString("Password");
            if ((userN.equals(userName)) &&(pass.equals(password)) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean getValid(String userName) throws SQLException {
        String sql = "SELECT * FROM user WHERE UserName = ?";
        ResultSet resultSet = SQLUtill.execute(sql, userName);
        if (resultSet.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean RecoverUpdate(String userName, String password) throws SQLException {
        String sql = "UPDATE user SET Password = ? WHERE UserName = ?";
        return SQLUtill.execute(sql,password, userName );
    }
}
