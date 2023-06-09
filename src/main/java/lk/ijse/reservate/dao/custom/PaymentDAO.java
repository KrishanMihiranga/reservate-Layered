package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<payment> {

    public  double generateTotValue() throws SQLException ;

    public  List<String> getGIds() throws SQLException ;

    public  List<String> getOIds() throws SQLException;

    public  List<String> getHIds() throws SQLException;

    public  List<String> getRds() throws SQLException;

}
