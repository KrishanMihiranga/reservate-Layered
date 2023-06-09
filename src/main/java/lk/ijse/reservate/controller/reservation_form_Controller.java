package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.*;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.*;

import lk.ijse.reservate.entity.RoomReservationDetails;
import lk.ijse.reservate.entity.hallReservation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class reservation_form_Controller {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXComboBox<String> cmbGuestId;

    @FXML
    private JFXComboBox<String> cmbHallNumber;

    @FXML
    private JFXComboBox<String> cmbRoomNumber;

    @FXML
    private JFXTextField txtHallReservationId;

    @FXML
    private JFXTextField txtRoomReservationId;

    @FXML
    private JFXButton btnReserveRoom;

    @FXML
    private DatePicker txtCheckInDate;

    @FXML
    private DatePicker txtCheckOutDate;

    @FXML
    private JFXButton btnReserveHall;

    @FXML
    private JFXButton btnCancelHall;

    @FXML
    private JFXButton btnCancelRoom;
   @FXML
   private JFXButton btnReservationDetails;

   @FXML
   private Label lblRoom;
   @FXML
   private Label lblHall;

    RoomReservationDetailsBO roomReservationDetailsBO = (RoomReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATIONDETAILS);
    HallReservationDetailsBO hallReservationDetailsBO = (HallReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATIONDETAILS);
    HallReservationBO hallReservationBO = (HallReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALLRESERVATION);
    HallBO hallBO = (HallBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.HALL);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    GuestBO guestBO = (GuestBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);
    RoomReservationBO roomReservationBO = (RoomReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOMRESERVATION);

    public void initialize(){
        loadGuestIds();
        loadRoomIds();
        loadHallIds();
        generateNextId();
        txtCheckInDate.setValue(LocalDate.now());
        txtCheckOutDate.setValue(null);
        cmbGuestId.setValue(null);
    }

    private void generateNextId() {
        try {
            String nextId = hallReservationBO.getNextId();
            txtHallReservationId.setText(nextId);
            String nextRId = roomReservationBO.getNextId();
            txtRoomReservationId.setText(nextRId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadGuestIds(){
        try{
            List<String> gIds = guestBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String guestIds : gIds){
                obList.add(guestIds);
            }
            cmbGuestId.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void loadRoomIds(){
        try{
            List<String> rIds = roomBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String roomIds : rIds){
                obList.add(roomIds);
            }
            cmbRoomNumber.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void loadHallIds(){
        try{
            List<String> hIds = hallBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hallIds : hIds){
                obList.add(hallIds);
            }
            cmbHallNumber.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnReserveRoomOnAction(ActionEvent event) {
        String CheckIn = String.valueOf(txtCheckInDate.getValue());
        String CheckOut = String.valueOf(txtCheckOutDate.getValue());
        String GuestId = String.valueOf(cmbGuestId.getValue());
        String RoomNumber = String.valueOf(cmbRoomNumber.getValue());
        String RoomReservationId = txtRoomReservationId.getText();

        try{
            boolean isSaved = roomReservationBO.Order(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnReserveHallOnAction(ActionEvent actionEvent) {
        String CheckIn = String.valueOf(txtCheckInDate.getValue());
        String CheckOut = String.valueOf(txtCheckOutDate.getValue());
        String GuestId = String.valueOf(cmbGuestId.getValue());
        String HallReservationId = txtHallReservationId.getText();
        String HallNumber = String.valueOf(cmbHallNumber.getValue());

        try{
            boolean isSaved =hallReservationBO.Order(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Hall Reserved!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void btnCancelRoomOnAction(ActionEvent actionEvent) {
        String roomNumber = cmbRoomNumber.getValue();
        String reservationId = txtRoomReservationId.getText();

        try{
           // boolean isSaved = RoomReservationModel.remove(reservationId);
          //  boolean isRemoved = roomReservationBO.delete(reservationId);
            boolean isRemoved = roomReservationDetailsBO.removeR(reservationId);
            if(isRemoved){

                new Alert(Alert.AlertType.CONFIRMATION, "Room Reservation Canceled!").show();
            }
        }catch(Exception e){

            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnCancelHallOnAction(ActionEvent actionEvent) {
        String hallNumber = cmbHallNumber.getValue();
        String reservationId = txtHallReservationId.getText();

        try{
          //  boolean isSaved =HallReservationModel.remove(hallNumber);
           boolean isRemoved = hallReservationDetailsBO.removeH(reservationId);
            if(isRemoved){

                new Alert(Alert.AlertType.CONFIRMATION, "Hall Reservation Canceled!").show();
            }
        }catch(Exception e){

            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnReservationDetailsOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/reservation_details_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void cmbRoomNumberOnAction(ActionEvent actionEvent) {
        cmbHallNumber.setValue(null);
        lblHall.setText(null);
        String RoomNumber = cmbRoomNumber.getValue();
        try{
            boolean isSaved =roomReservationBO.isValid(RoomNumber);
            if(isSaved){
                lblRoom.setText("*Already Reserved");
                lblRoom.setStyle("-fx-text-fill: red;");
            }else{
                lblRoom.setText("*Available");
                lblRoom.setStyle("-fx-text-fill: green;");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
       String roomnumber = cmbRoomNumber.getValue();
        RoomReservationDTO roomReservation = null;
        RoomReservationDetailsDTO rd = null;
        try {
            try {
                 roomReservation = roomReservationBO.setRFields(roomnumber);
                 rd = roomReservationDetailsBO.setFields(roomnumber);
            }
           catch (Exception e){

           }
            if (lblRoom.getText().equals("*Already Reserved")) {
                    cmbGuestId.setValue(roomReservation.getGuestId());
                    txtCheckInDate.setValue(LocalDate.parse(roomReservation.getCheckIn()));
                    txtCheckOutDate.setValue(LocalDate.parse(roomReservation.getCheckOut()));
                    cmbRoomNumber.setValue(roomReservation.getRoomNumber());
                    txtRoomReservationId.setText(rd.getRoomReservationId());
            }else{
                initialize();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }

    }

    public void cmbHallNumberOnAction(ActionEvent actionEvent) {
        cmbRoomNumber.setValue(null);
        lblRoom.setText(null);
        String HallNumber = cmbHallNumber.getValue();
        try{
            boolean isSaved =hallReservationBO.isValid(HallNumber);
            if(isSaved){
                lblHall.setText("*Already Reserved");
                lblHall.setStyle("-fx-text-fill: red;");
            }else{
                lblHall.setText("*Available");
                lblHall.setStyle("-fx-text-fill: green;");
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
        String hallNumber =cmbHallNumber.getValue();
        try {
            HallReservationDTO hallReservation = null;
            HallReservationDetailsDTO hd= null;
            try {
                hallReservation = hallReservationBO.setHFields(hallNumber);
                hd = hallReservationDetailsBO.setFields(hallNumber);
            }catch (Exception e){

            }

            if (lblHall.getText().equals("*Already Reserved")) {
                cmbGuestId.setValue(hallReservation.getGuestId());
                txtCheckInDate.setValue(LocalDate.parse(hallReservation.getCheckIn()));
                txtCheckOutDate.setValue(LocalDate.parse(hallReservation.getCheckOut()));
                cmbHallNumber.setValue(hallReservation.getHallNumber());
                txtHallReservationId.setText(hd.getHallReservationId());
            }else{
                initialize();
            }
        } catch (Exception e) {
           e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }


    }

    public void cmbrIdOnAction(ActionEvent actionEvent) {
        String rId = txtRoomReservationId.getText();
        try {
            RoomReservationDTO reservation = roomReservationBO.setFields(rId);
            if (reservation != null)
            {
                txtCheckInDate.setValue(LocalDate.parse(reservation.getCheckIn()));
                txtCheckOutDate.setValue(LocalDate.parse(reservation.getCheckOut()));
                txtRoomReservationId.setText(reservation.getRoomReservationId());
                cmbRoomNumber.setValue(reservation.getRoomNumber());
                cmbGuestId.setValue(reservation.getGuestId());
            } else {
                new Alert(Alert.AlertType.WARNING, "no Reservation found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }

    public void txtHallReservationIdOnAction(ActionEvent actionEvent) {
        String id = txtHallReservationId.getText();
        try {
            HallReservationDTO hallreservation= hallReservationBO.setFields(id);
            if (hallreservation != null)
            {
                txtCheckInDate.setValue(LocalDate.parse(hallreservation.getCheckIn()));
                txtCheckOutDate.setValue(LocalDate.parse(hallreservation.getCheckOut()));
                txtHallReservationId.setText(hallreservation.getHallReservationId());
                cmbHallNumber.setValue(hallreservation.getHallNumber());
                cmbGuestId.setValue(hallreservation.getGuestId());
            } else {
                new Alert(Alert.AlertType.WARNING, "no Reservation found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
