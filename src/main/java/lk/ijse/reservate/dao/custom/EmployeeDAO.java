package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    public boolean save(String empId, String nic, String fullName, String address, String mobile, String date, String jobRole, String email) throws SQLException;

    public List<String> getIds() throws SQLException;

    public boolean update(String empId, String nic, String fullName, String address, String mobile, String date, String jobRole, String email) throws SQLException;

    public boolean delete(String empId) throws SQLException;

    public String generateNextEmpId() throws SQLException;

    public String splitEmpId(String currentOrderId);

    public EmployeeDTO setFields(String empId) throws SQLException;

    public boolean roleCheck(String empId) throws SQLException;
}