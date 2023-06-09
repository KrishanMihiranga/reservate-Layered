package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.entity.Complaint;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintBO extends CrudBO<Complaint> {

    public  List<String> getRIds() throws SQLException ;
    public  List<String> getGIds() throws SQLException ;
    public  List<String> getHIds() throws SQLException ;
    public  List<String> getMIds() throws SQLException ;

    public  List<ComplaintDTO> getAll() throws SQLException ;

   public Complaint setFields(String complainid) throws SQLException, ClassNotFoundException;

}
