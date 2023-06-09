package lk.ijse.reservate.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.mealplans;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansDAO extends CrudDAO<mealplans> {


    public  List<mealplans> getAll() throws SQLException ;

    public  String getItems(String packageId) throws SQLException ;

}
