package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.reservate.model.UserModel;

import java.io.IOException;

public class login_form_Controller {
    @FXML
    private ImageView closebtn;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private Label lblWP;

    static Stage stage;
    static Stage stage2;
    public void closeBtnOnMouseClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
       try{
           String userName = userNameField.getText();
           String password = passwordField.getText();
           boolean isElegible = UserModel.elegibleCheck(userName, password);
           if(isElegible){
               stage = (Stage) userNameField.getScene().getWindow();
               stage.hide();
               FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/receptionist_dashboard_form.fxml"));
               Scene scene = new Scene(fxmlLoader.load());
               stage2 = new Stage();
               stage2.setScene(scene);
               stage2.setFullScreen(true);
               stage2.show();

           }else if((userNameField.getText().equals("admin"))&&(passwordField.getText().equals("123"))){
               stage = (Stage) userNameField.getScene().getWindow();
               stage.hide();
               FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/admin_dashboard_form.fxml"));
               Scene scene = new Scene(fxmlLoader.load());
               stage2 = new Stage();
               stage2.setScene(scene);
               stage2.setFullScreen(true);
               stage2.show();
           }else{
               lblWP.setText("Invalid Username or Password");
               new Alert(Alert.AlertType.ERROR, "Login Unsuccessful").show();
           }
       }catch (Exception e){
           System.out.println(e);
       }

    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {

        passwordField.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        loginBtnOnAction(actionEvent);
    }

    public void txtUserNameOnMouseClicked(MouseEvent mouseEvent) {
        lblWP.setText("");
    }

    public void forgotPasswoordOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/recover_password_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage2 = new Stage();
        stage2.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.show();
    }
}
