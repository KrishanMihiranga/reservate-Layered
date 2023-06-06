package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.mealOrderDTO;
import lk.ijse.reservate.dto.selectMealDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOrderDAOImpl {

    public static String generateNextId() throws SQLException {
        String sql = "SELECT MealOrderId FROM mealorder ORDER BY MealOrderId DESC LIMIT 1";

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
            String newId = String.format("O%04d", newNum);
            return newId;
        }
        return "O0001";
    }

    public static boolean save(String orderId, String guestId, String packageId, String date, String qty) throws SQLException {
        String sql = "INSERT INTO mealorder(MealOrderId, GuestId, PackageId, Date, Qty)VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, orderId,guestId, packageId, date, qty);
    }

    public static boolean update(String orderId, String guestId, String packageId, String date, String qty) throws SQLException {
        String sql ="UPDATE mealorder SET GuestId = ?, PackageId = ?, Date = ?, Qty = ? WHERE MealOrderId = ?";
        return CrudUtil.execute(sql, guestId, packageId, date, qty, orderId);
    }

    public static boolean remove(String orderId) throws SQLException {
        String sql = "DELETE FROM mealorder WHERE MealOrderId = ?";
        return CrudUtil.execute(sql, orderId);
    }

    public static selectMealDTO setFields(String id) throws SQLException {
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            String MealOrderId=resultSet.getString(1);
            String Qty=resultSet.getString(2);
            String GuestId=resultSet.getString(3);
            String PackageId=resultSet.getString(4);
            String Date=resultSet.getString(5);

            return new selectMealDTO(MealOrderId, Qty, GuestId, PackageId, Date);
        }
        return null;
    }
    public static mealOrderDTO getFields(String id) throws SQLException {
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            String MealOrderId=resultSet.getString(1);
            String Qty=resultSet.getString(2);
            String GuestId=resultSet.getString(3);
            String PackageId=resultSet.getString(4);
            String Date=resultSet.getString(5);

            return new mealOrderDTO(MealOrderId, Qty, GuestId, PackageId, Date);
        }
        return null;
    }

    public static String getQty(String value) throws SQLException {
        String qty;
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, value);
        if (resultSet.next()){
            qty = resultSet.getString("Qty");
            return qty;
        }
        return null;
    }

    public static boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException {
    Connection con = null;
    try{
        con= DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);
        boolean isSaved = MealOrderDAOImpl.save(orderId, guestId, packageId, date, qty);
        if(isSaved){
            boolean isAdded= MealOrderDetailsDAOImpl.save(orderId, packageId);
            if (isAdded){
                con.commit();
                return true;
            }
        }
        return false;
    }catch (SQLException e){
        e.printStackTrace();
        con.rollback();
        return false;
    }finally {
        con.setAutoCommit(true);
    }

    }
}
