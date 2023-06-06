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
import lk.ijse.reservate.dto.GuestDTO;
import lk.ijse.reservate.dao.custom.impl.GuestDAOImpl;
import lk.ijse.reservate.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class guests_form_Controller {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXButton btnAddGuest;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextField txtGuestId;

    @FXML
    private JFXTextField txtGuestName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private JFXButton btnUpdateGuest;
    @FXML
    private JFXButton btnRemoveGuest;

    public void initialize(){
        loadUserIds();
        generateNextId();
        txtDate.setValue(LocalDate.now());
    }

    private void generateNextId() {
        try {
            String nextId = GuestDAOImpl.generateNextId();
            txtGuestId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadUserIds() {
        try{
            List<String> uIds = UserDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String userIds : uIds){
                obList.add(userIds);
            }
            cmbUserId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnAddGuestOnAction(ActionEvent event) {

        String GuestId = txtGuestId.getText();
        String UserId= cmbUserId.getValue();
        String Nic = txtNic.getText();
        String Fullname =txtGuestName.getText();
        String Address =txtAddress.getText();
        String Mobile =txtContact.getText();
        String Date = String.valueOf(txtDate.getValue());
        String Email =txtEmail.getText();

        boolean isMatch = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?").matcher(Email).matches();
        if (isMatch){
            txtEmail.setStyle("-fx-text-fill: black");
            if(GuestId.isEmpty() || UserId.isEmpty() || Nic.isEmpty() || Fullname.isEmpty() || Address.isEmpty() || Mobile.isEmpty() || Date.isEmpty() || Email.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = GuestDAOImpl.save(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "GuestDTO Added!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtEmail.setStyle("-fx-text-fill: red");
        }

    }

    public void btnUpdateGuestOnAction(ActionEvent actionEvent) {
        String GuestId = txtGuestId.getText();
        String UserId= cmbUserId.getValue();
        String Nic = txtNic.getText();
        String Fullname =txtGuestName.getText();
        String Address =txtAddress.getText();
        String Mobile =txtContact.getText();
        String Date = String.valueOf(txtDate.getValue());
        String Email =txtEmail.getText();

        boolean isMatch = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?").matcher(Email).matches();
        if (isMatch){
            txtEmail.setStyle("-fx-text-fill: black");
            if(GuestId.isEmpty() || UserId.isEmpty() || Nic.isEmpty() || Fullname.isEmpty() || Address.isEmpty() || Mobile.isEmpty() || Date.isEmpty() || Email.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = GuestDAOImpl.update(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "GuestDTO Updated!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtEmail.setStyle("-fx-text-fill: red");
        }


    }

    public void btnRemoveGuestOnAction(ActionEvent actionEvent) {
        String GuestId = txtGuestId.getText();
        try{
            boolean isSaved = GuestDAOImpl.remove(GuestId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "GuestDTO Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtGuestIdOnAction(ActionEvent actionEvent) {
        String GuestId = txtGuestId.getText();
        try {
            GuestDTO guestDTO = GuestDAOImpl.setFields(GuestId);
            if (GuestId != null)
            {
                txtGuestId.setText(guestDTO.getGuestId());
                cmbUserId.setValue(guestDTO.getUserId());
                txtNic.setText(guestDTO.getNic());
                txtGuestName.setText(guestDTO.getFullname());
                txtAddress.setText(guestDTO.getAddress());
                txtContact.setText(guestDTO.getMobile());
                txtDate.setValue(LocalDate.parse(guestDTO.getDate()));
                txtEmail.setText(guestDTO.getEmail());

            } else {
                new Alert(Alert.AlertType.WARNING, "no GuestDTO found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
