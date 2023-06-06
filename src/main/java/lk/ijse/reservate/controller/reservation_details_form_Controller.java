package lk.ijse.reservate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.dto.HallReservationDetailsDTO;
import lk.ijse.reservate.dto.RoomReservationDetailsDTO;
import lk.ijse.reservate.entity.HallReservationDetails;
import lk.ijse.reservate.entity.RoomReservationDetails;
import lk.ijse.reservate.dao.custom.impl.HallReservationDetailsDAOImpl;
import lk.ijse.reservate.dao.custom.impl.RoomReservationDetailsDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class reservation_details_form_Controller implements Initializable {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private TableView<HallReservationDetails> tblHallReservation;

    @FXML
    private TableColumn<?, ?> colHallReservationId;

    @FXML
    private TableColumn<?, ?> colHallNumber;

    @FXML
    private TableView<RoomReservationDetails> tblRoomReservation;

    @FXML
    private TableColumn<?, ?> colRoomReservationId;

    @FXML
    private TableColumn<?, ?> colRoomNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    void setCellValueFactory() {
        colHallReservationId.setCellValueFactory(new PropertyValueFactory<>("HallReservationId"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("HallNumber"));
        colRoomReservationId.setCellValueFactory(new PropertyValueFactory<>("RoomReservationId"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
    }

    void getAll() {
        try {
            ObservableList<HallReservationDetails> obList = FXCollections.observableArrayList();
            ObservableList<RoomReservationDetails> ob = FXCollections.observableArrayList();

            List<HallReservationDetailsDTO> hresList = HallReservationDetailsDAOImpl.getAll();
            List<RoomReservationDetailsDTO> rresList = RoomReservationDetailsDAOImpl.getAll();


            for(HallReservationDetailsDTO hall : hresList) {
                obList.add(new HallReservationDetails(
                        hall.getHallReservationId(),
                        hall.getHallNumber()
                ));
            }
            for(RoomReservationDetailsDTO room : rresList) {
                ob.add(new RoomReservationDetails(
                        room.getRoomReservationId(),
                        room.getRoomNumber()
                ));
            }
            tblHallReservation.setItems(obList);
            tblRoomReservation.setItems(ob);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }


}
