package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.MealOrderDetailsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOrderDetailsDAOImpl implements MealOrderDetailsDAO {

    @Override
    public boolean add(String orderId, String packageId) throws SQLException {
        String sql = "INSERT INTO mealorderdetails(PackageId, MealOrderId)VALUES(?, ?)";
        return SQLUtill.execute(sql, packageId,orderId);
    }

    @Override
    public boolean delete(String orderId) throws SQLException {
        String sql = "DELETE FROM mealorderdetails WHERE MealOrderId = ?";
        return SQLUtill.execute(sql, orderId);
    }

    @Override
    public String getpkg(String cmbOrderId) throws SQLException {
        String pkgId;
        String sql = "SELECT * FROM mealorderdetails WHERE MealOrderId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, cmbOrderId);
        if (resultSet.next()){
            pkgId= resultSet.getString("PackageId");
            return pkgId;
        }
        return null;
    }

}
