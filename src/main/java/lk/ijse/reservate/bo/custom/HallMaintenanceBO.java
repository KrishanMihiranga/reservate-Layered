package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.HallMaintenanceDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallMaintenanceBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallMaintenanceDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(HallMaintenanceDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallMaintenanceDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
