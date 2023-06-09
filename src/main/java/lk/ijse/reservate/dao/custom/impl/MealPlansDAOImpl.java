package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dao.custom.MealPlansDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.entity.mealplans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlansDAOImpl implements MealPlansDAO {


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT packageId FROM meal ORDER BY packageId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(mealplans entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO meal(PackageId, MealPlan, MealType, Description, Price) VALUES(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getPackageId(),entity.getMealPlan(), entity.getMealType(),entity.getDescription(),entity.getPrice());
    }

    @Override
    public boolean update(mealplans entity) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE meal SET MealPlan = ?, MealType = ?, Description = ?, Price = ? WHERE PackageId = ?";
        return SQLUtill.execute(sql, entity.getMealPlan(),entity.getMealType(),entity.getDescription(),entity.getPrice(), entity.getPackageId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql= "DELETE FROM meal WHERE PackageId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT packageId FROM meal";
        List<String> packageIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            packageIds.add(resultSet.getString(1));
        }
        return packageIds;
    }

    @Override
    public mealplans setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal WHERE PackageId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String PackageId=resultSet.getString(1);
            String MealPlan = resultSet.getString(2);
            String MealType = resultSet.getString(3);
            String Description = resultSet.getString(4);
            Double Price=resultSet.getDouble(5);
            return new mealplans(PackageId, MealPlan, MealType, Description, Price);
        }
        return null;
    }

    @Override
    public List<mealplans> getAll() throws SQLException {
        String sql = "SELECT * FROM meal";
        List<mealplans> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new mealplans(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return data;
    }

    @Override
    public String getItems(String packageId) throws SQLException {
        String items;
        String sql = "SELECT * FROM meal WHERE PackageId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, packageId);
        if (resultSet.next()){
            items = resultSet.getString("Description");
            return items;
        }
        return null;
    }

}
