package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.ComplaintDTO;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintBO extends SuperBO {

    public  List<String> getRIds() throws SQLException ;
    public  List<String> getGIds() throws SQLException ;
    public  List<String> getHIds() throws SQLException ;
    public  List<String> getMIds() throws SQLException ;

    public  List<ComplaintDTO> getAll() throws SQLException ;

   public ComplaintDTO setFields(String complainid) throws SQLException, ClassNotFoundException;

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(ComplaintDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(ComplaintDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;


}
