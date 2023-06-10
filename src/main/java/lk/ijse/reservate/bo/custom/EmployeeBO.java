package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.EmployeeDTO;
import lk.ijse.reservate.entity.employee;

import java.sql.SQLException;

public interface EmployeeBO extends CrudBO<EmployeeDTO> {
    public boolean roleCheck(String empId) throws SQLException;
}