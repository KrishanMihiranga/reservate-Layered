package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.UserBO;
import lk.ijse.reservate.dao.DAOFactory;
import lk.ijse.reservate.dao.custom.UserDAO;
import lk.ijse.reservate.dto.UserDTO;
import lk.ijse.reservate.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO user = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return user.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("U%04d", newNum);
            return newId;
        }
        return "U0001";
    }

    @Override
    public boolean add(UserDTO entity) throws SQLException, ClassNotFoundException {
        return user.add(new User(entity.getUserId(), entity.getEmpId(), entity.getUserName(),entity.getPassword()));

    }

    @Override
    public boolean update(UserDTO entity) throws SQLException, ClassNotFoundException {

        return user.update(new User(entity.getUserId(),entity.getEmpId(),entity.getUserName(),entity.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return user.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return user.getIds();
    }

    @Override
    public UserDTO setFields(String id) throws SQLException, ClassNotFoundException {

        User u = user.setFields(id);
        return new UserDTO(u.getUserId(), u.getEmpId(), u.getUserName(), u.getPassword());

    }

    @Override
    public boolean empCheck(String empId) throws SQLException {
       return user.empCheck(empId);
    }

    @Override
    public boolean elegibleCheck(String userName, String password) throws SQLException {
       return user.elegibleCheck(userName, password);
    }

    @Override
    public boolean getValid(String userName) throws SQLException {
        return user.getValid(userName);
    }

    @Override
    public boolean RecoverUpdate(String userName, String password) throws SQLException {
       return user.RecoverUpdate(userName, password);
    }
}
