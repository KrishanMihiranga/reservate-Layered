package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.GuestDTO;
import lk.ijse.reservate.entity.guest;

import java.sql.SQLException;

public interface GuestBO extends CrudBO<GuestDTO> {

   public  String getName(String value) throws SQLException ;
}
