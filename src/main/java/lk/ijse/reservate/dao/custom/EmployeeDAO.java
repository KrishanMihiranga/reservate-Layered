package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.EmployeeDTO;
import lk.ijse.reservate.entity.employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<employee> {
    public boolean roleCheck(String empId) throws SQLException;
}