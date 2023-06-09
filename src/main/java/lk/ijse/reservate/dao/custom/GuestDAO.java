package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.dto.GuestDTO;
import lk.ijse.reservate.entity.guest;

import java.sql.SQLException;
import java.util.List;

public interface GuestDAO extends CrudDAO<guest> {

   public  String getName(String value) throws SQLException ;
}
