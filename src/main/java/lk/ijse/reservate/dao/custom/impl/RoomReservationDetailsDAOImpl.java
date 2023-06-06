package lk.ijse.reservate.dao.custom.impl;

import lk.ijse.reservate.dto.RoomReservationDetailsDTO;
import lk.ijse.reservate.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDetailsDAOImpl {

    public static List<RoomReservationDetailsDTO> getAll() throws SQLException {

        String sql = "SELECT * FROM RoomReservationDetailsDTO";
        List<RoomReservationDetailsDTO> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new RoomReservationDetailsDTO(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return data;
    }

    public static boolean save(String roomReservationId, String roomNumber) throws SQLException {
        String sql ="INSERT INTO RoomReservationDetailsDTO(RoomReservationId, RoomNumber) VALUES(?, ?)";
        return CrudUtil.execute(sql, roomReservationId, roomNumber);
    }

    public static boolean remove(String roomNumber) throws SQLException {
        String sql = "DELETE FROM RoomReservationDetailsDTO WHERE RoomReservationId = ?";
        return CrudUtil.execute(sql, roomNumber);
    }

    public static boolean removeR(String roomReservationId) throws SQLException {
        String sql = "DELETE FROM RoomReservationDetailsDTO WHERE RoomReservationId = ?";
        return CrudUtil.execute(sql, roomReservationId);
    }

    public static RoomReservationDetailsDTO setFields(String roomnumber) throws SQLException {
        String sql = "SELECT * FROM RoomReservationDetailsDTO WHERE RoomNumber = ?";
        ResultSet resultSet = CrudUtil.execute(sql, roomnumber);
        if (resultSet.next()) {
            String RoomReservationId = resultSet.getString(1);
            String RoomNumber = resultSet.getString(2);
            return new RoomReservationDetailsDTO(RoomReservationId, RoomNumber);
        }
        return null;
    }

    public static String getRoom(String value) throws SQLException {
        String roomId;
        String sql = "SELECT * FROM RoomReservationDetailsDTO WHERE RoomReservationId = ?";
        ResultSet resultSet =CrudUtil.execute(sql, value);
        if (resultSet.next()){
            roomId= resultSet.getString("RoomNumber");
            return roomId;
        }
        return null;
    }

}
