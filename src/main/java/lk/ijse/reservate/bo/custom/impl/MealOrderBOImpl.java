package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.MealOrderBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.MealOrderDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.entity.mealOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MealOrderBOImpl implements MealOrderBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT MealOrderId FROM mealorder ORDER BY MealOrderId DESC LIMIT 1";
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
            String newId = String.format("O%04d", newNum);
            return newId;
        }
        return "O0001";
    }

    @Override
    public boolean add(mealOrder entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO mealorder(MealOrderId, GuestId, PackageId, Date, Qty)VALUES(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getMealOrderId(),entity.getGuestId(),entity.getPackageId(),entity.getDate(),entity.getQty());
    }

    @Override
    public boolean update(mealOrder entity) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE mealorder SET GuestId = ?, PackageId = ?, Date = ?, Qty = ? WHERE MealOrderId = ?";
        return SQLUtill.execute(sql, entity.getGuestId(),entity.getPackageId(),entity.getDate(),entity.getQty(),entity.getMealOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM mealorder WHERE MealOrderId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public mealOrder setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String MealOrderId=resultSet.getString(1);
            String Qty=resultSet.getString(2);
            String GuestId=resultSet.getString(3);
            String PackageId=resultSet.getString(4);
            String Date=resultSet.getString(5);
            return new mealOrder(MealOrderId, Qty, GuestId, PackageId, Date);
        }
        return null;
    }

    @Override
    public mealOrder getFields(String id) throws SQLException {
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String MealOrderId=resultSet.getString(1);
            String Qty=resultSet.getString(2);
            String GuestId=resultSet.getString(3);
            String PackageId=resultSet.getString(4);
            String Date=resultSet.getString(5);
            return new mealOrder(MealOrderId, Qty, GuestId, PackageId, Date);
        }
        return null;
    }

    @Override
    public String getQty(String value) throws SQLException {
        String qty;
        String sql = "SELECT * FROM mealorder WHERE MealOrderId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            qty = resultSet.getString("Qty");
            return qty;
        }
        return null;
    }

    @Override
    public boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException {
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
