package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.MealPlansBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.MealPlansDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.dto.MealPlansDTO;
import lk.ijse.reservate.entity.Complaint;
import lk.ijse.reservate.entity.mealplans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlansBOImpl implements MealPlansBO {

    MealPlansDAO mealPlansDAO = (MealPlansDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALPLANS);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return mealPlansDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
       return mealPlansDAO.splitId(currentId);
    }

    @Override
    public boolean add(MealPlansDTO entity) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.add(new mealplans(entity.getPackageId(),entity.getMealPlan(), entity.getMealType(),entity.getDescription(),entity.getPrice()));

    }

    @Override
    public boolean update(MealPlansDTO entity) throws SQLException, ClassNotFoundException {
        return mealPlansDAO.update(new mealplans(entity.getPackageId(),entity.getMealPlan(), entity.getMealType(),entity.getDescription(),entity.getPrice()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
      return mealPlansDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
       return mealPlansDAO.getIds();
    }

    @Override
    public MealPlansDTO setFields(String id) throws SQLException, ClassNotFoundException {
        mealplans mealplans = mealPlansDAO.setFields(id);
        return new MealPlansDTO(mealplans.getPackageId(),mealplans.getMealPlan(),mealplans.getMealType(),mealplans.getDescription(),mealplans.getPrice());

    }

    @Override
    public List<MealPlansDTO> getAll() throws SQLException {

        List<MealPlansDTO> data = new ArrayList<>();
        List<mealplans> allEntityData = mealPlansDAO.getAll();

        for (mealplans mealplans : allEntityData) {
            data.add(new MealPlansDTO(mealplans.getPackageId(),mealplans.getMealPlan(),mealplans.getMealType(),mealplans.getDescription(),mealplans.getPrice()));
        }
      return data;
    }

    @Override
    public String getItems(String packageId) throws SQLException {
        return mealPlansDAO.getItems(packageId);
    }

}
