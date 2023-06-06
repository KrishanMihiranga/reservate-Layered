package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOrderDetailsDAOImpl {
    public static boolean save(String orderId, String packageId) throws SQLException {
        String sql = "INSERT INTO mealorderdetails(PackageId, MealOrderId)VALUES(?, ?)";
        return CrudUtil.execute(sql, packageId,orderId);
    }
    public static boolean remove(String orderId) throws SQLException {
        String sql = "DELETE FROM mealorderdetails WHERE MealOrderId = ?";
        return CrudUtil.execute(sql, orderId);
    }

    public static String getpkg(String cmbOrderId) throws SQLException {
        String pkgId;
        String sql = "SELECT * FROM mealorderdetails WHERE MealOrderId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, cmbOrderId);
        if (resultSet.next()){
            pkgId= resultSet.getString("PackageId");
            return pkgId;
        }
        return null;
    }
}
