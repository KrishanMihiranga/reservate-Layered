package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dao.SQLUtill;
import lk.ijse.reservate.dao.custom.RoomReservationDetailsDAO;
import lk.ijse.reservate.entity.RoomReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDetailsDAOImpl implements RoomReservationDetailsDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RoomReservationDetails entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO RoomReservationDetails(RoomReservationId, RoomNumber) VALUES(?, ?)";
        return SQLUtill.execute(sql, entity.getRoomReservationId(),entity.getRoomNumber());
    }

    @Override
    public boolean update(RoomReservationDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM RoomReservationDetails WHERE RoomReservationId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservationDetails setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetails WHERE RoomNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String RoomReservationId = resultSet.getString(1);
            String RoomNumber = resultSet.getString(2);
            return new RoomReservationDetails(RoomReservationId, RoomNumber);
        }
        return null;
    }

    @Override
    public List<RoomReservationDetails> getAll() throws SQLException {
        String sql = "SELECT * FROM RoomReservationDetails";
        List<RoomReservationDetails> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new RoomReservationDetails(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }

    @Override
    public boolean removeR(String roomReservationId) throws SQLException {
        String sql = "DELETE FROM RoomReservationDetails WHERE RoomReservationId = ?";
        return SQLUtill.execute(sql, roomReservationId);
    }

    @Override
    public String getRoom(String value) throws SQLException {
        String roomId;
        String sql = "SELECT * FROM RoomReservationDetails WHERE RoomReservationId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            roomId= resultSet.getString("RoomNumber");
            return roomId;
        }
        return null;
    }


}
