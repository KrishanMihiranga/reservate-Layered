package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.MealOrderDetailsBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.MealOrderDetailsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOrderDetailsBOImpl implements MealOrderDetailsBO {

    MealOrderDetailsDAO mealOrderDetailsDAO = (MealOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDERDETAILS);

    @Override
    public boolean add(String orderId, String packageId) throws SQLException {
        return mealOrderDetailsDAO.add(packageId,orderId);
    }

    @Override
    public boolean delete(String orderId) throws SQLException {
        return mealOrderDetailsDAO.delete(orderId);
    }

    @Override
    public String getpkg(String cmbOrderId) throws SQLException {
        return mealOrderDetailsDAO.getpkg(cmbOrderId);
    }

}
