package lk.ijse.reservate.bo.custom.impl;

import lk.ijse.reservate.bo.custom.HallReservationDetailsBO;
import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.HallReservationDetailsDAO;
import lk.ijse.reservate.entity.HallReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallReservationDetailsBOImpl implements HallReservationDetailsBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(HallReservationDetails entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO HallReservationDetailsDTO(HallReservationId, HallNumber) VALUES(?, ?)";
        return SQLUtill.execute(sql, entity.getHallReservationId(), entity.getHallNumber());
    }

    @Override
    public boolean update(HallReservationDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM HallReservationDetailsDTO WHERE HallNumber = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HallReservationDetails setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HallReservationDetailsDTO WHERE HallNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String hallReservationId = resultSet.getString(1);
            String HallNumber = resultSet.getString(2);
            return new HallReservationDetails(hallReservationId, HallNumber);
        }
        return null;
    }

    @Override
    public List<HallReservationDetails> getAll() throws SQLException {
        String sql = "SELECT * FROM HallReservationDetailsDTO";
        List<HallReservationDetails> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new HallReservationDetails(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }

    @Override
    public boolean removeH(String hallReservationId) throws SQLException {
        String sql = "DELETE FROM HallReservationDetailsDTO WHERE HallReservationId = ?";
        return SQLUtill.execute(sql, hallReservationId);
    }

    @Override
    public String getHall(String value) throws SQLException {
        String hallId;
        String sql = "SELECT * FROM HallReservationDetailsDTO WHERE HallReservationId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            hallId= resultSet.getString("HallNumber");
            return hallId;
        }
        return null;
    }


}
