package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.HallDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallBO extends SuperBO {

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(HallDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
