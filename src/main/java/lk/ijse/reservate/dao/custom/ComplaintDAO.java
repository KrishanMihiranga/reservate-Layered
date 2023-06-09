package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.entity.Complaint;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintDAO extends CrudDAO<Complaint> {

    public  List<String> getRIds() throws SQLException ;
    public  List<String> getGIds() throws SQLException ;
    public  List<String> getHIds() throws SQLException ;
    public  List<String> getMIds() throws SQLException ;

    public  List<Complaint> getAll() throws SQLException ;

   public Complaint setFields(String complainid) throws SQLException, ClassNotFoundException;

}
