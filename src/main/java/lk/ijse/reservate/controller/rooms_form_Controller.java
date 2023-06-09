package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.dto.Room;
import lk.ijse.reservate.dto.User;
import lk.ijse.reservate.model.RoomModel;
import lk.ijse.reservate.model.UserModel;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class rooms_form_Controller {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnAddRoom;

    @FXML
    private JFXTextField txtRoomNumber;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private JFXButton btnUpdateRoom;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXComboBox<String> cmbroomType;

    public void initialize(){
     setStatus();
     generateNextRoomNumber();
     setType();
    }

    private void setType() {
        ObservableList<String> data = FXCollections.observableArrayList(
                "A/C",
                "Non A/C"
        );
        cmbroomType.setItems(data);
    }

    private void generateNextRoomNumber() {
        try {
            String nextRoomNumber = RoomModel.generateNextRoomNumber();
            txtRoomNumber.setText(nextRoomNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatus(){
        ObservableList<String> data = FXCollections.observableArrayList(
                "Available",
                "Under Maintenance"
        );
        cmbStatus.setItems(data);
    }
    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        String RoomNumber = txtRoomNumber.getText();
        String RoomType= cmbroomType.getValue();
        String Price = txtPrice.getText();
        String Status = String.valueOf(cmbStatus.getValue());

        boolean isMatch = Pattern.compile("^\\d+(\\.\\d{1,2})?$").matcher(txtPrice.getText()).matches();

        if (isMatch){
            txtPrice.setStyle("-fx-text-fill: black");
            if (RoomNumber.isEmpty() || RoomType.isEmpty() || Status.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = RoomModel.save(RoomNumber, RoomType, String.valueOf(Price), Status);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Added!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtPrice.setStyle("-fx-text-fill: red");
            txtPrice.setText(String.valueOf(Price));
        }

    }

    public void btnUpdateRoomOnAction(ActionEvent actionEvent) {
        String RoomNumber = txtRoomNumber.getText();
        String RoomType= cmbroomType.getValue();
        Double Price = Double.valueOf(txtPrice.getText());
        String Status = String.valueOf(cmbStatus.getValue());

        boolean isMatch = Pattern.compile("^\\d+(\\.\\d{1,2})?$").matcher(txtPrice.getText()).matches();

        if (isMatch){
            txtPrice.setStyle("-fx-text-fill: black");
            if (RoomNumber.isEmpty() || RoomType.isEmpty() || Status.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = RoomModel.update(RoomNumber, RoomType, String.valueOf(Price), Status);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtPrice.setStyle("-fx-text-fill: red");
            txtPrice.setText(String.valueOf(Price));
        }


    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String RoomNumber = txtRoomNumber.getText();
        try{
            boolean isSaved = RoomModel.remove(RoomNumber);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtRoomNumberOnAction(ActionEvent actionEvent) {
        String RoomNumber = txtRoomNumber.getText();
        try {
            Room room = RoomModel.setFields(RoomNumber);
            if (room != null)
            {

                txtRoomNumber.setText(room.getRoomNumber());
                cmbroomType.setValue(room.getRoomType());
                txtPrice.setText(String.valueOf(room.getPrice()));
                cmbStatus.setValue(room.getStatus());


            } else {
                new Alert(Alert.AlertType.WARNING, "no Room found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
