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
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.GuestBO;
import lk.ijse.reservate.bo.custom.UserBO;
import lk.ijse.reservate.dto.GuestDTO;

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

    GuestBO guestBO = (GuestBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUEST);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        loadUserIds();
        generateNextId();
        txtDate.setValue(LocalDate.now());
    }

    private void generateNextId() {
        try {
            String nextId = guestBO.getNextId();
            txtGuestId.setText(nextId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadUserIds() {
        try{
            List<String> uIds = userBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String userIds : uIds){
                obList.add(userIds);
            }
            cmbUserId.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
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
                    boolean isSaved = guestBO.add(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Guest Added!").show();
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
                    boolean isSaved = guestBO.update(GuestId, UserId, Nic, Fullname, Address, Mobile, Date, Email);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Guest Updated!").show();
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
            boolean isSaved = guestBO.delete(GuestId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Guest Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtGuestIdOnAction(ActionEvent actionEvent) {
        String GuestId = txtGuestId.getText();
        try {
            GuestDTO guest = guestBO.setFields(GuestId);
            if (GuestId != null)
            {
                txtGuestId.setText(guest.getGuestId());
                cmbUserId.setValue(guest.getUserId());
                txtNic.setText(guest.getNic());
                txtGuestName.setText(guest.getFullname());
                txtAddress.setText(guest.getAddress());
                txtContact.setText(guest.getMobile());
                txtDate.setValue(LocalDate.parse(guest.getDate()));
                txtEmail.setText(guest.getEmail());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Guest found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
