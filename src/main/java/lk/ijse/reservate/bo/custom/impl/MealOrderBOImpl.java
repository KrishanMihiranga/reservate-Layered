package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.MealOrderBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.MealOrderDAO;
import lk.ijse.reservate.dao.custom.MealOrderDetailsDAO;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.MealOrderDTO;
import lk.ijse.reservate.entity.mealOrder;
import lk.ijse.reservate.entity.mealOrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MealOrderBOImpl implements MealOrderBO {

    MealOrderDAO mealOrderDAO = (MealOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDER);
    MealOrderDetailsDAO mealOrderDetailsDAO = (MealOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEALORDERDETAILS);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return mealOrderDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
       return mealOrderDAO.splitId(currentId);
    }

    @Override
    public boolean add(MealOrderDTO entity) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.add(new mealOrder(entity.getMealOrderId(),entity.getGuestId(),entity.getPackageId(),entity.getDate(),entity.getQty()));

    }

    @Override
    public boolean update(MealOrderDTO entity) throws SQLException, ClassNotFoundException {
        return mealOrderDAO.update(new mealOrder(entity.getGuestId(),entity.getPackageId(),entity.getDate(),entity.getQty(),entity.getMealOrderId()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
      return mealOrderDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MealOrderDTO setFields(String id) throws SQLException, ClassNotFoundException {

        mealOrder mealOrder = mealOrderDAO.setFields(id);
        return new MealOrderDTO(mealOrder.getMealOrderId(), mealOrder.getQty(), mealOrder.getGuestId(), mealOrder.getPackageId(), mealOrder.getDate());

    }

    @Override
    public MealOrderDTO getFields(String id) throws SQLException {
        mealOrder mealOrder = mealOrderDAO.getFields(id);
        return new MealOrderDTO(mealOrder.getMealOrderId(), mealOrder.getQty(), mealOrder.getGuestId(), mealOrder.getPackageId(), mealOrder.getDate());
    }

    @Override
    public String getQty(String value) throws SQLException {
        return mealOrderDAO.getQty(value);
    }

    @Override
    public boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = mealOrderDAO.add(new mealOrder(orderId, guestId, packageId, date, qty));
            if(isSaved){
                boolean isAdded= mealOrderDetailsDAO.add(orderId, packageId);
                if (isAdded){
                    con.commit();
                    return true;
                }
            }
            return false;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

}
