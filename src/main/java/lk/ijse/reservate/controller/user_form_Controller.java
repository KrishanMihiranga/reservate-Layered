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
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.EmployeeBO;
import lk.ijse.reservate.bo.custom.UserBO;
import lk.ijse.reservate.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;
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

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize(){
        generateNextUserId();
        loadEmpIds();
    }

    private void generateNextUserId() {
        try {
            String nextUserId = userBO.getNextId();
            txtUserId.setText(nextUserId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmpIds(){
        try{
            List<String> eIds = employeeBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String empIds : eIds){
                obList.add(empIds);
            }
            comboEmpId.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
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
        boolean isReceptionist = employeeBO.roleCheck(EmpId);
        boolean validEmployee = userBO.empCheck(EmpId);

        if (isValid && isReceptionist && validEmployee) {
            txtPassword.setStyle("-fx-text-fill: black");
            if (UserId.isEmpty() || UserName.isEmpty() || Password.isEmpty() || EmpId.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty Values !").show();
            }else {
                try{
                    boolean isSaved = userBO.add(new UserDTO(UserId, EmpId, UserName, Password));
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
            boolean isSaved =userBO.update(new UserDTO(UserId, EmpId, UserName, Password));
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
            boolean isSaved =userBO.delete(UserId);
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
            UserDTO user = userBO.setFields(UserId);
            if (user != null)
            {
                txtUserId.setText(user.getUserId());
                txtUserName.setText(user.getUserName());
                txtPassword.setText(user.getPassword());
                comboEmpId.setValue(user.getEmpId());

            } else {
                new Alert(Alert.AlertType.WARNING, "no User found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
