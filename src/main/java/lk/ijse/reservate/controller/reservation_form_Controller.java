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
import lk.ijse.reservate.dao.custom.impl.*;
import lk.ijse.reservate.dto.*;

import java.io.IOException;
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
            String nextId = HallReservationDAOImpl.generateNextId();
            txtHallReservationId.setText(nextId);
            String nextRId = RoomReservationDAOImpl.generateNextId();
            txtRoomReservationId.setText(nextRId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadGuestIds(){
        try{
            List<String> gIds = GuestDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String guestIds : gIds){
                obList.add(guestIds);
            }
            cmbGuestId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void loadRoomIds(){
        try{
            List<String> rIds = RoomDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String roomIds : rIds){
                obList.add(roomIds);
            }
            cmbRoomNumber.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void loadHallIds(){
        try{
            List<String> hIds = HallDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hallIds : hIds){
                obList.add(hallIds);
            }
            cmbHallNumber.setItems(obList);
        }catch (SQLException e){
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
            boolean isSaved = RoomReservationDAOImpl.Order(CheckIn, CheckOut, RoomReservationId, GuestId, RoomNumber);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "RoomDTO Reserved!").show();
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
            boolean isSaved = HallReservationDAOImpl.Order(CheckIn, CheckOut, HallReservationId, GuestId, HallNumber);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "HallDTO Reserved!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void btnCancelRoomOnAction(ActionEvent actionEvent) {
        String roomNumber = cmbRoomNumber.getValue();
        String reservationId = txtRoomReservationId.getText();

        try{
           // boolean isSaved = RoomReservationDAOImpl.remove(reservationId);
            boolean isRemoved = RoomReservationDetailsDAOImpl.remove(reservationId);

            if(isRemoved){

                new Alert(Alert.AlertType.CONFIRMATION, "RoomDTO Reservation Canceled!").show();
            }
        }catch(Exception e){

            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnCancelHallOnAction(ActionEvent actionEvent) {
        String hallNumber = cmbHallNumber.getValue();
        String reservationId = txtHallReservationId.getText();

        try{
          //  boolean isSaved =HallReservationDAOImpl.remove(hallNumber);
           boolean isRemoved = HallReservationDetailsDAOImpl.removeH(reservationId);
            if(isRemoved){

                new Alert(Alert.AlertType.CONFIRMATION, "HallDTO Reservation Canceled!").show();
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
            boolean isSaved = RoomReservationDAOImpl.isValid(RoomNumber);
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
        try {
            roomReservationDTO roomReservationDTO = RoomReservationDAOImpl.setRFields(roomnumber);
            RoomReservationDetailsDTO rd = RoomReservationDetailsDAOImpl.setFields(roomnumber);
            if (lblRoom.getText().equals("*Already Reserved")) {
                    cmbGuestId.setValue(roomReservationDTO.getGuestId());
                    txtCheckInDate.setValue(LocalDate.parse(roomReservationDTO.getCheckIn()));
                    txtCheckOutDate.setValue(LocalDate.parse(roomReservationDTO.getCheckOut()));
                    cmbRoomNumber.setValue(roomReservationDTO.getRoomNumber());
                    txtRoomReservationId.setText(rd.getRoomReservationId());
            }else{
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }

    }

    public void cmbHallNumberOnAction(ActionEvent actionEvent) {
        cmbRoomNumber.setValue(null);
        lblRoom.setText(null);
        String HallNumber = cmbHallNumber.getValue();
        try{
            boolean isSaved = HallReservationDAOImpl.isValid(HallNumber);
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
            hallReservationDTO hallReservationDTO = HallReservationDAOImpl.setHFields(hallNumber);
            HallReservationDetailsDTO hd = HallReservationDetailsDAOImpl.setFields(hallNumber);
            if (lblHall.getText().equals("*Already Reserved")) {
                cmbGuestId.setValue(hallReservationDTO.getGuestId());
                txtCheckInDate.setValue(LocalDate.parse(hallReservationDTO.getCheckIn()));
                txtCheckOutDate.setValue(LocalDate.parse(hallReservationDTO.getCheckOut()));
                cmbHallNumber.setValue(hallReservationDTO.getHallNumber());
                txtHallReservationId.setText(hd.getHallReservationId());
            }else{
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }


    }

    public void cmbrIdOnAction(ActionEvent actionEvent) {
        String rId = txtRoomReservationId.getText();
        try {
            roomReservationDTO reservation = RoomReservationDAOImpl.setFields(rId);
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
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }

    public void txtHallReservationIdOnAction(ActionEvent actionEvent) {
        String id = txtHallReservationId.getText();
        try {
            hallReservationDTO hallreservation= HallReservationDAOImpl.setFields(id);
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
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}