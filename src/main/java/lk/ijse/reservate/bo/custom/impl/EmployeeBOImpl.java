package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.EmployeeBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.EmployeeDAO;
import lk.ijse.reservate.dto.EmployeeDTO;
import lk.ijse.reservate.entity.employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return employeeDAO.getNextId();
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
    public boolean add(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new employee(entity.getEmpId(), entity.getNic(), entity.getFullname(), entity.getAddress(), entity.getMobile(),entity.getDate(), entity.getJobRole(),entity.getEmail()));

    }

    @Override
    public boolean update(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new employee(entity.getEmpId(),entity.getNic(),entity.getFullname(),entity.getAddress(), entity.getMobile(),entity.getDate(), entity.getJobRole(), entity.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIds();
    }

    @Override
    public EmployeeDTO setFields(String id) throws SQLException, ClassNotFoundException {
     employee emp = employeeDAO.setFields(id);
     return new EmployeeDTO(emp.getEmpId(), emp.getNic(),emp.getFullname(), emp.getAddress(),emp.getMobile(), emp.getDate(),emp.getJobRole(),emp.getEmail());

    }

    @Override
    public boolean roleCheck(String empId) throws SQLException {
        return employeeDAO.roleCheck(empId);

    }

}
