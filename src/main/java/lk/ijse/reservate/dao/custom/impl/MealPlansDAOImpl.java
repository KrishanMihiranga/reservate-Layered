package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.MealPlansDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlansDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT packageId FROM meal ORDER BY packageId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public static String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            int lastNum = Integer.parseInt(currentOrderId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    public static boolean save(String packageId, String mealPlan, String mealType, String description, String price) throws SQLException {
        String sql ="INSERT INTO meal(PackageId, MealPlan, MealType, Description, Price) VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, packageId,mealPlan, mealType,description, price);
    }
    public static List<String> getIds() throws SQLException{
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT packageId FROM meal";
        List<String> packageIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            packageIds.add(resultSet.getString(1));
        }
        return packageIds;
    }

    public static boolean update(String packageId, String mealPlan, String mealType, String description, String price) throws SQLException {
        String sql ="UPDATE meal SET MealPlan = ?, MealType = ?, Description = ?, Price = ? WHERE PackageId = ?";
        return CrudUtil.execute(sql, mealPlan, mealType, description, price, packageId);
    }

    public static boolean remove(String packageId) throws SQLException {
        String sql= "DELETE FROM meal WHERE PackageId = ?";
        return CrudUtil.execute(sql, packageId);
    }

    public static MealPlansDTO setFields(String packageId) throws SQLException {
        String sql = "SELECT * FROM meal WHERE PackageId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, packageId);

        if(resultSet.next()) {

            String PackageId=resultSet.getString(1);
            String MealPlan = resultSet.getString(2);
            String MealType = resultSet.getString(3);
            String Description = resultSet.getString(4);
            Double Price=resultSet.getDouble(5);

            return new MealPlansDTO(PackageId, MealPlan, MealType, Description, Price);
        }
        return null;
    }

    public static List<MealPlansDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM meal";

        List<MealPlansDTO> data = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new MealPlansDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return data;
    }

    public static String getItems(String packageId) throws SQLException {
        String items;
        String sql = "SELECT * FROM meal WHERE PackageId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, packageId);
        if (resultSet.next()){
             items = resultSet.getString("Description");
             return items;
        }
        return null;
    }
}
