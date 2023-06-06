package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.reservate.dao.custom.impl.DashboardDAOImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

public class ReceptionistDashboardFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane dashoardPane;

    @FXML
    private Text lblTotalRooms;

    @FXML
    private Text lblTotalHalls;

    @FXML
    private Text lblBookedhalls;

    @FXML
    private Text lblBookedRooms;

    @FXML
    private Text lblComplaints;

    @FXML
    private Text lblTopic;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Text txtTime;
    @FXML
    private Text txtDate;

    private int year;
    private int month;
    private int datee;

    public void initialize(){
        setTxtDateTime();
        setlable();
    }
    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtTime.setText(formattedTime);
    }
    private void setTxtDateTime() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DateFormat date = new SimpleDateFormat("yyy:MM:dd");
        Calendar cal = Calendar.getInstance();

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        datee = cal.get(Calendar.DATE);
        txtDate.setText(year + " : " + month + " : " + datee);

    }



    private void setlable(){
        try {
            int totRooms = DashboardDAOImpl.getTotalRooms();
            lblTotalRooms.setText(String.valueOf(totRooms));

            int totHalls = DashboardDAOImpl.getTotalHalls();
            lblTotalHalls.setText(String.valueOf(totHalls));

            int bookedHalls = DashboardDAOImpl.getBookedHalls();
            lblBookedhalls.setText(String.valueOf(bookedHalls));

            int bookedRooms = DashboardDAOImpl.getBookedRooms();
            lblBookedRooms.setText(String.valueOf(bookedRooms));

            int complaints = DashboardDAOImpl.getComplaints();
            lblComplaints.setText(String.valueOf(complaints));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Error in set Label!").show();
        }

    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Reservation");
    }

    public void btnDashboardOnAction(ActionEvent event) throws IOException {
//        Stage thisStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader= new FXMLLoader(ReceptionistDashboardFormController.class.getResource("/view/receptionist_dashboard_form.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        thisStage.setScene(scene);
        FXMLLoader fxmlLoader = new FXMLLoader(ReceptionistDashboardFormController.class.getResource("/view/receptionist_dashboard_form.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Set the stage to full screen
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);

    }

    public void btnSelectMealOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/select_meal_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Select Meal");
    }

    public void btnMaintenanceOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/mark_maintenance_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Maintenance");
    }

    public void btnGuestOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/guests_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Guests");
    }

    public void btnpaymentOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Payments");
    }

    public void btnComplaintOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/complaint_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Complaints");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {


            Stage loginStage = (Stage) btnLogout.getScene().getWindow();
            loginStage.close();
            FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();

        }
    }

    public void btnAboutUsOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/aboutus_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("About Us");
    }

    public void btnContactOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/contactus_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Contact");
    }

    public void btnSendMailOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/email_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Send Mail");
    }
}
