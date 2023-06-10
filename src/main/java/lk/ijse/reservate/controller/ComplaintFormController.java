package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.ComplaintBO;
import lk.ijse.reservate.dto.ComplaintDTO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ComplaintFormController implements Initializable {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXComboBox<String> cmbHallResrvationId;

    @FXML
    private JFXComboBox<String> cmbRoomReservationId;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private DatePicker Date;

    @FXML
    private JFXTextField txtComplaintId;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private JFXComboBox<String> cmbGuestId;

    @FXML
    private JFXComboBox<String> cmbMealOrderId;

    @FXML
    private JFXButton btnUpdateComplain;
    @FXML
    private JFXButton btnRemove;
    ComplaintBO complaintBO = (ComplaintBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COMPLAINT);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGuestIds();
        loadMealOrderIds();
        loadHallreservationIds();
        loadRoomReservationIds();
        generateNextId();
        Date.setValue(LocalDate.now());
        setTime();

    }


    private void setTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        txtTime.setText(formattedTime);


    }
    private void generateNextId() {
        try {
            String nextId = complaintBO.getNextId();
            txtComplaintId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRoomReservationIds() {
        try{
            List<String> RIds = complaintBO.getRIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String RoomIds : RIds){
                obList.add(RoomIds);
            }
            cmbRoomReservationId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadHallreservationIds() {
        try{
            List<String> HIds = complaintBO.getHIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String HallIds : HIds){
                obList.add(HallIds);
            }
            cmbHallResrvationId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadMealOrderIds() {
        try{
            List<String> MIds = complaintBO.getMIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String MealIds : MIds){
                obList.add(MealIds);
            }
            cmbMealOrderId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadGuestIds() {
        try{
            List<String> GIds = complaintBO.getGIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String GuestIds : GIds){
                obList.add(GuestIds);
            }
            cmbGuestId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String complainid= txtComplaintId.getText();
        String date= String.valueOf(Date.getValue());
        String time=txtTime.getText();
        String GuestId=cmbGuestId.getValue();
        String MealId=cmbMealOrderId.getValue();
        String RoomId=cmbRoomReservationId.getValue();
        String HallId=cmbHallResrvationId.getValue();
        String Description=txtDescription.getText();
        try{
            boolean isSaved= complaintBO.add(new ComplaintDTO(complainid, date, time, GuestId, MealId, RoomId, HallId, Description));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Complaint Added!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnUpdateComplainOnAction(ActionEvent actionEvent) {
        String complainid= txtComplaintId.getText();
        String date= String.valueOf(Date.getValue());
        String time=txtTime.getText();
        String GuestId=cmbGuestId.getValue();
        String MealId=cmbMealOrderId.getValue();
        String RoomId=cmbRoomReservationId.getValue();
        String HallId=cmbHallResrvationId.getValue();
        String Description=txtDescription.getText();
        try{
            boolean isSaved= complaintBO.update(new ComplaintDTO(complainid, date, time, GuestId, MealId, RoomId, HallId, Description));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Complaint Added!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String complainid= txtComplaintId.getText();
        try{
            boolean isSaved= complaintBO.delete(complainid);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Complaint Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtComplaintIdOnAction(ActionEvent actionEvent) {
        String complainid= txtComplaintId.getText();
        try {
            ComplaintDTO complaint = complaintBO.setFields(complainid);
            if (complaint != null)
            {
                txtComplaintId.setText(complaint.getComplaintId());
                Date.setValue(LocalDate.parse(complaint.getDate()));
                txtTime.setText(complaint.getTime());
                cmbGuestId.setValue(complaint.getGuestid());
                cmbMealOrderId.setValue(complaint.getMealorderid());
                cmbRoomReservationId.setValue(complaint.getRoomreservationid());
                cmbHallResrvationId.setValue(complaint.getHallreservationid());
                txtDescription.setText(complaint.getDescription());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Complaint found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }


}
