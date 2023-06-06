package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.dao.custom.impl.HallMaintenanceDAOImpl;
import lk.ijse.reservate.dao.custom.impl.HallDAOImpl;
import lk.ijse.reservate.dao.custom.impl.RoomMaintenanceDAOImpl;
import lk.ijse.reservate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.reservate.dto.hallMaintenanceDTO;
import lk.ijse.reservate.dto.roomMaintenanceDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MarkMaintenanceFormController {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXComboBox<String> cmbHallNumber;

    @FXML
    private JFXComboBox<String> cmbroomNumber;

    @FXML
    private JFXButton btnMarkRoom;
    @FXML
    private JFXButton btnMarkHall;

    @FXML
    private JFXButton btnUpdateRoom;

    @FXML
    private JFXButton btnUpdateHall;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField txtRoomMaintenanceId;
    @FXML
    private JFXTextField txtHallMaintenanceId;

    @FXML
    private JFXTextField txtStartTime;

    @FXML
    private JFXTextField txtEndTime;

    @FXML
    private JFXButton btnCancelRoom;
    @FXML
    private JFXButton btnCancelHall;

    public void initialize(){
        loadRoomIds();
        loadHallIds();
        generateNextId();
        date.setValue(LocalDate.now());
        setTime();
    }

    private void setTime() {
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = currentTime;
        LocalTime endTime = startTime.plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);
        txtStartTime.setText(formattedStartTime);
        txtEndTime.setText(formattedEndTime);


    }

    private void generateNextId() {
        try {
            String nextHId = HallMaintenanceDAOImpl.generateNextId();
            String nextRId = RoomMaintenanceDAOImpl.generateNextId();
            txtHallMaintenanceId.setText(nextHId);
            txtRoomMaintenanceId.setText(nextRId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadHallIds() {
        try{
            List<String> HallIds = HallDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hIds : HallIds){
                obList.add(hIds);
            }
            cmbHallNumber.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadRoomIds() {
        try{
            List<String> RoomIds = RoomDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String rIds : RoomIds){
                obList.add(rIds);
            }
            cmbroomNumber.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnMarkRoomOnAction(ActionEvent event) {
        String MaintenanceId = txtRoomMaintenanceId.getText();
        String Date = String.valueOf(date.getValue());
        String RoomNumber= cmbroomNumber.getValue();
        String StartTime= txtStartTime.getText();
        String EndTime= txtEndTime.getText();

        if (cmbroomNumber.getValue() !=null) {
            try {
                boolean isSaved = RoomMaintenanceDAOImpl.save(MaintenanceId, RoomNumber, Date, StartTime, EndTime);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Added!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Can't pass empty values!").show();
        }

    }
    @FXML
    void btnMarkHallOnAction(ActionEvent event) {
        String MaintenanceId=txtHallMaintenanceId.getText();
        String Date= String.valueOf(date.getValue());
        String hallNumber=cmbHallNumber.getValue();
        String StartTime=txtStartTime.getText();
        String EndTime=txtEndTime.getText();
        if (cmbHallNumber.getValue() !=null) {
            try {
                boolean isSaved = HallMaintenanceDAOImpl.save(MaintenanceId, hallNumber, Date, StartTime, EndTime);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Added!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Can't pass empty values!").show();
        }
    }

    public void btnUpdateRoomOnAction(ActionEvent actionEvent) {
        String MaintenanceId = txtRoomMaintenanceId.getText();
        String Date = String.valueOf(date.getValue());
        String RoomNumber= cmbroomNumber.getValue();
        String StartTime= txtStartTime.getText();
        String EndTime= txtEndTime.getText();

        try{
            boolean isSaved = RoomMaintenanceDAOImpl.updateRoom(MaintenanceId, RoomNumber, Date, StartTime,EndTime);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Added!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            e.printStackTrace();
        }
    }

    public void btnUpdateHallOnAction(ActionEvent actionEvent) {
        String MaintenanceId=txtHallMaintenanceId.getText();
        String Date= String.valueOf(date.getValue());
        String hallNumber=cmbHallNumber.getValue();
        String StartTime=txtStartTime.getText();
        String EndTime=txtEndTime.getText();
        try{
            boolean isSaved = HallMaintenanceDAOImpl.updateHall(MaintenanceId, hallNumber, Date, StartTime,EndTime);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Added!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnCancelRoomOnAction(ActionEvent actionEvent) {
        String MaintenanceId = txtRoomMaintenanceId.getText();
        try{
            boolean isSaved = RoomMaintenanceDAOImpl.remove(MaintenanceId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            e.printStackTrace();
        }
    }

    public void btnCancelHallOnAction(ActionEvent actionEvent) {
        String MaintenanceId=txtHallMaintenanceId.getText();
        try{
            boolean isSaved = HallMaintenanceDAOImpl.remove(MaintenanceId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtRoomMaintenanceIdOnAction(ActionEvent actionEvent) {
        String MaintenanceId = txtRoomMaintenanceId.getText();
        try {
           roomMaintenanceDTO rm = RoomMaintenanceDAOImpl.setFields(MaintenanceId);
            if (rm != null)
            {
                txtRoomMaintenanceId.setText(rm.getRoomMaintenanceId());
                date.setValue(LocalDate.parse(rm.getDate()));
                cmbroomNumber.setValue(rm.getRoomNumber());
                txtStartTime.setText(rm.getStartTime());
                txtEndTime.setText(rm.getEndTime());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Maintenance found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }

    public void txtHallMaintenanceIdOnAction(ActionEvent actionEvent) {
        String MaintenanceId=txtHallMaintenanceId.getText();

        try {
            hallMaintenanceDTO hm = HallMaintenanceDAOImpl.setFields(MaintenanceId);
            if (hm != null)
            {
                txtHallMaintenanceId.setText(hm.getHallMaintenanceId());
                date.setValue(LocalDate.parse(hm.getDate()));
                cmbHallNumber.setValue(hm.getHallNumber());
                txtStartTime.setText(hm.getStartTime());
                txtEndTime.setText(hm.getEndTime());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Maintenance found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
