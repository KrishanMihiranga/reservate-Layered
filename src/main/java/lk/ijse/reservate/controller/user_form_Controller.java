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
import lk.ijse.reservate.dto.Employee;
import lk.ijse.reservate.dto.User;
import lk.ijse.reservate.model.EmployeeModel;
import lk.ijse.reservate.model.UserModel;

import javax.swing.plaf.basic.BasicComboPopup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class user_form_Controller {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXButton btnAddUser;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXComboBox<String> comboEmpId;

    @FXML
    private JFXButton btnUpdateUser;

    @FXML
    private JFXButton btnRemove;

    public void initialize(){
        generateNextUserId();
        loadEmpIds();
    }

    private void generateNextUserId() {
        try {
            String nextUserId = UserModel.generateNextUserId();
            txtUserId.setText(nextUserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmpIds(){
        try{
            List<String> eIds = EmployeeModel.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String empIds : eIds){
                obList.add(empIds);
            }
            comboEmpId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) throws SQLException {
        String UserId = txtUserId.getText();
        String UserName = txtUserName.getText();
        String Password = txtPassword.getText();
        String EmpId    =comboEmpId.getValue();

        boolean isValid = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(Password).matches();
        boolean isReceptionist = EmployeeModel.roleCheck(EmpId);
        boolean validEmployee = UserModel.empCheck(EmpId);

        if (isValid && isReceptionist && validEmployee) {
            txtPassword.setStyle("-fx-text-fill: black");
            if (UserId.isEmpty() || UserName.isEmpty() || Password.isEmpty() || EmpId.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty Values !").show();
            }else {
                try{
                    boolean isSaved = UserModel.save(UserId, EmpId, UserName, Password);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }

        } else if(!isReceptionist){
            new Alert(Alert.AlertType.ERROR, "Invalid JobRole :/").show();
        }else if (!validEmployee){
            new Alert(Alert.AlertType.ERROR, "Already added!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtPassword.setStyle("-fx-text-fill: red");
            txtPassword.setText(Password);
        }

    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        String UserId = txtUserId.getText();
        String UserName = txtUserName.getText();
        String Password = txtPassword.getText();
        String EmpId    =comboEmpId.getValue();

        try{
            boolean isSaved =UserModel.update(UserId, EmpId, UserName, Password);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String UserId = txtUserId.getText();
        try{
            boolean isSaved =UserModel.remove(UserId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "User Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtUserIdOnAction(ActionEvent actionEvent) {
        String UserId = txtUserId.getText();
        try {
            User user = UserModel.setFields(UserId);
            if (user != null)
            {
                txtUserId.setText(user.getUserId());
                txtUserName.setText(user.getUserName());
                txtPassword.setText(user.getPassword());
                comboEmpId.setValue(user.getEmpId());

            } else {
                new Alert(Alert.AlertType.WARNING, "no User found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
