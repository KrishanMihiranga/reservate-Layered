package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.reservate.bo.BOFactory;
import lk.ijse.reservate.bo.custom.UserBO;


import java.sql.SQLException;
import java.util.regex.Pattern;

public class recover_password_form_Controller {


    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private Label lblCheck;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnDone;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnCheckOnAction(ActionEvent event) throws SQLException {
        String userName = txtUserName.getText();
        boolean isvalid = userBO.getValid(userName);
        if (isvalid){
        lblCheck.setText(null);
        txtPassword.setVisible(true);
        btnDone.setVisible(true);
        }else {
            lblCheck.setText("*Invalid Username");
        }
    }

    public void btnDoneOnAction(ActionEvent actionEvent) throws SQLException {
        String password =txtPassword.getText();
        String userName = txtUserName.getText();
        boolean isStrong = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(password).matches();
        if (isStrong) {
            boolean isValid = userBO.RecoverUpdate(userName, password);

            if (isValid) {
                new Alert(Alert.AlertType.CONFIRMATION, "Password Changed!").show();
                Stage stage = (Stage) btnDone.getScene().getWindow();
                stage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Recover Failed :/").show();
            }
        }

    }

    public void closeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
    }
}
