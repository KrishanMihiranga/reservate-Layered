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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.dto.EmployeeDTO;
import lk.ijse.reservate.dao.custom.impl.EmployeeDAOImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class employee_form_Controller {


    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXButton btnAddUser;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField empId;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField contact;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton btnUpdateUser;

    @FXML
    private Label lblValid;
    @FXML
    private  JFXButton btnRemove;

    @FXML
    private JFXComboBox<String> jobRole;

    public void initialize(){
        generateNextEmpId();
        date.setValue(LocalDate.now());
        setRole();
    }

    private void setRole() {
        ObservableList<String> data = FXCollections.observableArrayList(
                "Receptionist",
                "Housekeeping Attendant",
                "Chef",
                "Marketing Manager",
                "Technician"
        );
        jobRole.setItems(data);
    }

    private void generateNextEmpId() {
        try {
            String nextEmpId = EmployeeDAOImpl.generateNextEmpId();
            empId.setText(nextEmpId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) {

        String Nic=nic.getText();
        String Date = String.valueOf(date.getValue());
        String EmpId = empId.getText();
        String Address = address.getText();
        String FullName = fullName.getText();
        String Mobile = contact.getText();
        String JobRole = jobRole.getValue();
        String Email= email.getText();

        boolean isMatch = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?").matcher(Email).matches();
        boolean pN = Pattern.compile("^\\+?\\d{10,15}$").matcher(Mobile).matches();
        if (isMatch && pN){
            email.setStyle("-fx-text-fill: black");
            contact.setStyle("-fx-text-fill: black");
            if(Date.isEmpty() || EmpId.isEmpty() || Address.isEmpty() || FullName.isEmpty() || Mobile.isEmpty() || JobRole.isEmpty() || Email.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values !").show();
            }else {
                try {
                    boolean isSaved = EmployeeDAOImpl.save(EmpId, Nic, FullName, Address, Mobile, Date, JobRole, Email);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO Added!").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            if(!isMatch) {
                email.setStyle("-fx-text-fill: red");
                email.setText(Email);
            }else if(!pN){
                contact.setStyle("-fx-text-fill: red");
                contact.setText(Mobile);
            }
        }


    }

    @FXML
    void btnUpdateuserOnAction(ActionEvent event) {
        String Nic=nic.getText();
        String Date = String.valueOf(date.getValue());
        String EmpId = empId.getText();
        String Address = address.getText();
        String FullName = fullName.getText();
        String Mobile = contact.getText();
        String JobRole = jobRole.getValue();
        String Email= email.getText();
        boolean isMatch = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?").matcher(Email).matches();
        boolean pN = Pattern.compile("^\\+?\\d{10,15}$").matcher(Mobile).matches();
        if (isMatch && pN){
            email.setStyle("-fx-text-fill: black");
            contact.setStyle("-fx-text-fill: black");
            if(Date.isEmpty() || EmpId.isEmpty() || Address.isEmpty() || FullName.isEmpty() || Mobile.isEmpty() || JobRole.isEmpty() || Email.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values !").show();
            }else {
                try{
                    boolean isSaved = EmployeeDAOImpl.update(EmpId, Nic, FullName, Address, Mobile, Date, JobRole, Email);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO Updated!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            if(!isMatch) {
                email.setStyle("-fx-text-fill: red");
                email.setText(Email);
            }else if(!pN){
                contact.setStyle("-fx-text-fill: red");
                contact.setText(Mobile);
            }
        }


    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String EmpId = empId.getText();
        try{
            boolean isSaved = EmployeeDAOImpl.delete(EmpId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO Deleted!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "There is no matching employee!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void empIdOnAction(ActionEvent actionEvent) {
        String code = empId.getText();
        try {
            EmployeeDTO employeeDTO = EmployeeDAOImpl.setFields(code);
            if (employeeDTO != null)
            {
                nic.setText(employeeDTO.getNic());
                date.setValue(LocalDate.parse(String.valueOf(employeeDTO.getDate())));
                empId.setText(employeeDTO.getEmpId());
                address.setText(employeeDTO.getAddress());
                fullName.setText(employeeDTO.getFullname());
                contact.setText(employeeDTO.getMobile());
                jobRole.setValue(employeeDTO.getJobRole());
                email.setText(employeeDTO.getEmail());

            } else {
                new Alert(Alert.AlertType.WARNING, "no EmployeeDTO found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }

    }
}
