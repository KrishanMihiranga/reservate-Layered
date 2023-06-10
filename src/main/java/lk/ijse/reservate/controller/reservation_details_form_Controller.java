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
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.HallReservationDetailsBO;
import lk.ijse.reservate.bo.custom.RoomReservationDetailsBO;
import lk.ijse.reservate.dto.HallReservationDetailsDTO;
import lk.ijse.reservate.dto.RoomReservationDetailsDTO;
import lk.ijse.reservate.tdm.HallReservationDetailsTM;
import lk.ijse.reservate.tdm.RoomReservationDetailsTM;
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


    HallReservationDetailsBO hallReservationDetailsBO = (HallReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATIONDETAILS);
    RoomReservationDetailsBO roomReservationDetailsBO = (RoomReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATIONDETAILS);


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

            List<HallReservationDetailsDTO> hresList = hallReservationDetailsBO.getAll();
            List<RoomReservationDetailsDTO> rresList = roomReservationDetailsBO.getAll();


            for(HallReservationDetailsDTO hall : hresList) {
                obList.add(new HallReservationDetailsTM(
                        hall.getHallReservationId(),
                        hall.getHallNumber()
                ));
            }
            for(RoomReservationDetailsDTO room : rresList) {
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
