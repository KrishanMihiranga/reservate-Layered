package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.EmployeeBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.EmployeeDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT EmpId FROM employee ORDER BY EmpId DESC LIMIT 1";
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
            String newId = String.format("E%04d", newNum);
            return newId;
        }
        return "E0001";
    }

    @Override
    public boolean add(employee entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO employee(EmpID, Nic, FullName, Address, Mobile, Date, JobRole, Email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getEmpId(), entity.getNic(), entity.getFullname(), entity.getAddress(), entity.getMobile(),entity.getDate(), entity.getJobRole(),entity.getEmail());
    }

    @Override
    public boolean update(employee entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET Nic = ?, FullName = ?, Address = ?, Mobile = ?, Date = ?, JobRole = ?, Email = ? WHERE EmpID = ?";
        return SQLUtill.execute(sql,entity.getNic(),entity.getFullname(),entity.getAddress(), entity.getMobile(),entity.getDate(), entity.getJobRole(), entity.getEmail(),entity.getEmpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM employee WHERE EmpID = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT EmpId FROM employee";
        List<String> roomIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            roomIds.add(resultSet.getString(1));
        }
        return roomIds;
    }

    @Override
    public employee setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE EmpID = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String EmpId = resultSet.getString(1);
            String Nic = resultSet.getString(2);
            String Fullname = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String mobile = resultSet.getString(5);
            Date Date = java.sql.Date.valueOf(resultSet.getString(6));
            String JobRole = resultSet.getString(7);
            String Email = resultSet.getString(8);
            return new employee(EmpId, Nic, Fullname, Address, mobile, Date, JobRole, Email);
        }
        return null;
    }

    @Override
    public boolean roleCheck(String empId) throws SQLException {
        String sql = "SELECT JobRole FROM employee WHERE EmpID = ?";
        ResultSet resultSet = SQLUtill.execute(sql, empId);
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
