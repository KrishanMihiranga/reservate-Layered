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
import lk.ijse.reservate.dto.HallReservationDetails;
import lk.ijse.reservate.dto.RoomReservationDetails;
import lk.ijse.reservate.dto.tm.HallReservationDetailsTM;
import lk.ijse.reservate.dto.tm.RoomReservationDetailsTM;
import lk.ijse.reservate.model.HallReservationDetailsModel;
import lk.ijse.reservate.model.RoomReservationDetailsModel;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class reservation_details_form_Controller implements Initializable {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private TableView<HallReservationDetailsTM> tblHallReservation;

    @FXML
    private TableColumn<?, ?> colHallReservationId;

    @FXML
    private TableColumn<?, ?> colHallNumber;

    @FXML
    private TableView<RoomReservationDetailsTM> tblRoomReservation;

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
            ObservableList<HallReservationDetailsTM> obList = FXCollections.observableArrayList();
            ObservableList<RoomReservationDetailsTM> ob = FXCollections.observableArrayList();

            List<HallReservationDetails> hresList = HallReservationDetailsModel.getAll();
            List<RoomReservationDetails> rresList = RoomReservationDetailsModel.getAll();


            for(HallReservationDetails hall : hresList) {
                obList.add(new HallReservationDetailsTM(
                        hall.getHallReservationId(),
                        hall.getHallNumber()
                ));
            }
            for(RoomReservationDetails room : rresList) {
                ob.add(new RoomReservationDetailsTM(
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
