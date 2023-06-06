package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.EmployeeDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAOImpl {


    public static boolean save(String empId, String nic, String fullName, String address, String mobile, String date, String jobRole, String email) throws SQLException {
        String sql ="INSERT INTO employee(EmpID, Nic, FullName, Address, Mobile, Date, JobRole, Email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, empId, nic, fullName, address, mobile, date, jobRole, email );
    }


    public static List<String> getIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT EmpId FROM employee";
        List<String> roomIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            roomIds.add(resultSet.getString(1));
        }
        return roomIds;
    }

    public static boolean update(String empId, String nic, String fullName, String address, String mobile, String date, String jobRole, String email) throws SQLException {
        String sql = "UPDATE employee SET Nic = ?, FullName = ?, Address = ?, Mobile = ?, Date = ?, JobRole = ?, Email = ? WHERE EmpID = ?";
        return CrudUtil.execute(sql, nic, fullName, address, mobile, date, jobRole, email, empId);
    }

    public static boolean delete(String empId) throws SQLException {
        String sql ="DELETE FROM employee WHERE EmpID = ?";
        return CrudUtil.execute(sql, empId);
    }

    public static String generateNextEmpId() throws SQLException {
        String sql = "SELECT EmpId FROM employee ORDER BY EmpId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitEmpId(resultSet.getString(1));
        }
        return splitEmpId(null);
    }

    public static String splitEmpId(String currentOrderId) {
        if(currentOrderId != null) {
            int lastNum = Integer.parseInt(currentOrderId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("E%04d", newNum);
            return newId;
        }
        return "E0001";
    }

    public static EmployeeDTO setFields(String empId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, empId);

        if(resultSet.next()) {
            String EmpId = resultSet.getString(1);
            String Nic = resultSet.getString(2);
            String Fullname = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String mobile = resultSet.getString(5);
            Date Date = java.sql.Date.valueOf(resultSet.getString(6));
            String JobRole = resultSet.getString(7);
            String Email = resultSet.getString(8);


            return new EmployeeDTO(EmpId, Nic, Fullname, Address, mobile, Date, JobRole, Email);
        }
        return null;
    }

    public static boolean roleCheck(String empId) throws SQLException {
        String sql = "SELECT JobRole FROM employee WHERE EmpID = ?";
        ResultSet resultSet = CrudUtil.execute(sql, empId);
       if (resultSet.next()){
           String jobRole = resultSet.getString("JobRole");
           if (jobRole.equals("Receptionist")) {
               return true;
           } else {
               return false;
           }
       }else{
           return false;
       }
    }
}
