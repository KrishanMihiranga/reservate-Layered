package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.SuperBO;
import lk.ijse.reservate.dto.GuestDTO;

import java.sql.SQLException;
import java.util.List;

public interface GuestBO extends SuperBO {

   public  String getName(String value) throws SQLException ;
   public String getNextId() throws SQLException, ClassNotFoundException;

   public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

   public boolean add(GuestDTO entity) throws SQLException, ClassNotFoundException;

   public boolean update(GuestDTO entity) throws SQLException, ClassNotFoundException;

   public  boolean delete(String id) throws SQLException, ClassNotFoundException;

   public List<String> getIds() throws SQLException, ClassNotFoundException;

   public GuestDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
