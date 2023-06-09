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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

public class AdminDashboardFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane dashoardPane;

    @FXML
    private Text lblTotalRooms;

    @FXML
    private Text lblAvailablerooms;

    @FXML
    private Text lblAvailableHalls;

    @FXML
    private Text lblBookedhalls;

    @FXML
    private Text lblBookedRooms;

    @FXML
    private Text lblComplaints;

    @FXML
    private Text lblBookedhalls1;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnMealPlans;

    @FXML
    private JFXButton btnHalls;

    @FXML
    private JFXButton btnReports;

    @FXML
    private Text lblTopic;
    @FXML
    private Text txtTime;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private Text txtDate;
    @FXML
    private Label lbltotValue;

    private int year;
    private int month;
    private int datee;
    private Double totValue;
    public void initialize() throws SQLException {
        setTxtDateTime();
        setlable();
        setNetAmount();
    }

    private void setNetAmount() throws SQLException {
        totValue= paymentModel.generateTotValue();
        lbltotValue.setText(String.valueOf(totValue));
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
            int totRooms = DashboardModel.getTotalRooms();
            lblTotalRooms.setText(String.valueOf(totRooms));

            int bookedRooms = DashboardModel.getBookedRooms();
            lblBookedRooms.setText(String.valueOf(bookedRooms));

            int AvailableRooms = (totRooms-bookedRooms);
            lblAvailablerooms.setText(String.valueOf(AvailableRooms));

            int totHalls = DashboardModel.getTotalHalls();

            int bookedHalls = DashboardModel.getBookedHalls();
            lblBookedhalls.setText(String.valueOf(bookedHalls));

            int AvailableHalls= (totHalls-bookedHalls);
            lblAvailableHalls.setText(String.valueOf(AvailableHalls));

            int complaints = DashboardModel.getComplaints();
            lblComplaints.setText(String.valueOf(complaints));
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Error in set Label!").show();
        }

    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
//        Stage thisStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        FXMLLoader fxmlLoader= new FXMLLoader(ReceptionistDashboardFormController.class.getResource("/view/admin_dashboard_form.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//
//        thisStage.setScene(scene);
        FXMLLoader fxmlLoader = new FXMLLoader(ReceptionistDashboardFormController.class.getResource("/view/admin_dashboard_form.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Set the stage to full screen
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Employee");
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/user_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Users");
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/rooms_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Rooms");
    }

    public void btnMealPlansOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/meal_plans_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Meal Plans");
    }

    public void btnHallsOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/halls_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Banquet Halls");
    }

    public void btnreportsOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/report_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("Reports");
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


    public void btnComplaintsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/complaintTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAboutusOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/aboutus_form.fxml"));
        mainPane.getChildren().setAll(node);
        lblTopic.setText("About Us");
    }

    public void btncontactOnAction(ActionEvent actionEvent) throws IOException {
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
