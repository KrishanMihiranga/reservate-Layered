package lk.ijse.reservate.bo.custom;

import lk.ijse.reservate.bo.CrudBO;
import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.mealplans;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansBO extends CrudBO<mealplans> {


    public  List<mealplans> getAll() throws SQLException ;

    public  String getItems(String packageId) throws SQLException ;

}
