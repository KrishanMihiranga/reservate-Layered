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
import lk.ijse.reservate.dao.custom.impl.*;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFormController {

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXComboBox<String> cmbhallReservationId;

    @FXML
    private JFXComboBox<String> cmbRoomReservationId;

    @FXML
    private JFXButton btnDoPayment;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private JFXComboBox<String> cmbGuestId;

    @FXML
    private JFXComboBox<String> cmbOrderId;

    @FXML
    private Label lblAmount;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    private  JFXButton btnCancelPayment;
    private String rNumber;
    private Double roomPrice = null;
    private String pkgId;
    private int Qty;
    private Double mealPrice = null;
    private String hNumber;
    private Double hallPrice = null;
    private Double netAmount;
    private LocalDate hallstartDate;
    private LocalDate hallendDate;
    private LocalDate roomstartDate;
    private LocalDate roomendDate;
    private int halldays;
    private int roomdays;
    public void initialize(){
        loadGuestIds();
        loadOrderIds();
        loadHallreservationIds();
        loadRoomReservationIds();
        generateNextId();
        date.setValue(LocalDate.now());
        setTime();
    }
    private void setTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        txtTime.setText(formattedTime);


    }
    private void generateNextId() {
        try {
            String nextId = paymentDAOImpl.generateNextId();
            txtPaymentId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadRoomReservationIds() {
        try{
            List<String> RoomIds = paymentDAOImpl.getRds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String rIds : RoomIds){
                obList.add(rIds);
            }
            cmbRoomReservationId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadHallreservationIds() {
        try{
            List<String> HallIds = paymentDAOImpl.getHIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String hIds : HallIds){
                obList.add(hIds);
            }
            cmbhallReservationId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadOrderIds() {
        try{
            List<String> OrderIds = paymentDAOImpl.getOIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String oIds : OrderIds){
                obList.add(oIds);
            }
            cmbOrderId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadGuestIds() {
        try{
            List<String> GuestIds = paymentDAOImpl.getGIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String gIds : GuestIds){
                obList.add(gIds);
            }
            cmbGuestId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnDoPaymentOnAction(ActionEvent event) {
        String paymentId=txtPaymentId.getText();
        String GuestId=cmbGuestId.getValue();
        String MealOrderId=cmbOrderId.getValue();
        String HallReservationId=cmbhallReservationId.getValue();
        String RoomReservationId=cmbRoomReservationId.getValue();
        String Date= String.valueOf(date.getValue());
        double Amount= Double.parseDouble(lblAmount.getText());
        String Time = txtTime.getText();
if (!txtTime.getText().isEmpty()) {
    try {
        boolean isSaved = paymentDAOImpl.save(paymentId, GuestId, MealOrderId, HallReservationId, RoomReservationId, Date, Time, Amount);
        boolean isDeleted = MealOrderDetailsDAOImpl.remove(MealOrderId);
        boolean isRoomDeleted = RoomReservationDetailsDAOImpl.removeR(RoomReservationId);
        boolean isHallDeleted = HallReservationDetailsDAOImpl.removeH(HallReservationId);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "PaymentDTO Done!").show();
            lblAmount.setText("00.00");
        }
    } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
    }
}else{
    new Alert(Alert.AlertType.ERROR, "Can't pass empty values!").show();
}
    }

    public void btnCancelPaymentOnAction(ActionEvent actionEvent) {
        String paymentId=txtPaymentId.getText();
        try{
            boolean isSaved = paymentDAOImpl.remove(paymentId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "PaymentDTO Canceled!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtPaymentIdOnAction(ActionEvent actionEvent) {
        String paymentId=txtPaymentId.getText();
        try {
            PaymentDTO paymentDTO = paymentDAOImpl.setFields(paymentId);
            if (paymentDTO != null)
            {
                txtPaymentId.setText(paymentDTO.getPaymentid());
                cmbGuestId.setValue(paymentDTO.getGuestid());
                cmbOrderId.setValue(paymentDTO.getMealOrderId());
                cmbhallReservationId.setValue(paymentDTO.getHallreservationid());
                cmbRoomReservationId.setValue(paymentDTO.getRoomreservationid());
                date.setValue(LocalDate.parse(paymentDTO.getDate()));
                lblAmount.setText(paymentDTO.getAmount());
                txtTime.setText(paymentDTO.getTime());

            } else {
                new Alert(Alert.AlertType.WARNING, "no paymentDTO found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }

    public void cmbOrderIdOnAction(ActionEvent actionEvent) {
        String orderId= cmbOrderId.getValue();

        try {
            mealOrderDTO mealO = MealOrderDAOImpl.getFields(orderId);
            if (mealO != null)
            {
                pkgId =mealO.getPackageId();
                Qty= Integer.parseInt(mealO.getQty());
                MealPlansDTO mealPlansDTO = MealPlansDAOImpl.setFields(pkgId);
                if(mealPlansDTO !=null){
                    mealPrice = mealPlansDTO.getPrice();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "no payment found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }

        netAmount= Double.valueOf(lblAmount.getText());
        lblAmount.setText(String.valueOf(netAmount+(mealPrice*Qty)));
    }

    public void cmbhallReservationIdOnAction(ActionEvent actionEvent) {
        String hallR=cmbhallReservationId.getValue();

        try {
            hallReservationDTO hallReservationDTO = HallReservationDAOImpl.setFields(hallR);
            if (hallReservationDTO != null)
            {
                hNumber= hallReservationDTO.getHallNumber();
                hallstartDate = LocalDate.parse(hallReservationDTO.getCheckIn());
                hallendDate = LocalDate.parse(hallReservationDTO.getCheckOut());
                halldays = Math.toIntExact(ChronoUnit.DAYS.between(hallstartDate, hallendDate));
                if ((halldays==0)){
                    halldays=1;
                }

                HallDTO hallDTO = HallDAOImpl.setFields(hNumber);
                if (hallDTO !=null){
                    hallPrice = hallDTO.getPrice();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "no HallDTO found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }


        netAmount= Double.valueOf(lblAmount.getText());
        lblAmount.setText(String.valueOf(netAmount+(hallPrice * halldays)));
    }

    public void cmbRoomReservationIdOnAction(ActionEvent actionEvent) {
        String roomR=cmbRoomReservationId.getValue();

        try {
            roomReservationDTO reservation = RoomReservationDAOImpl.setFields(roomR);
            if (reservation != null)
            {
                rNumber=reservation.getRoomNumber();

                roomstartDate = LocalDate.parse(reservation.getCheckIn());
                roomendDate = LocalDate.parse(reservation.getCheckOut());
                roomdays = Math.toIntExact(ChronoUnit.DAYS.between(roomstartDate, roomendDate));
                if ((roomdays==0)){
                    roomdays=1;
                }

                RoomDTO roomDTO = RoomDAOImpl.setFields(rNumber);
                if (roomDTO !=null){
                    roomPrice = roomDTO.getPrice();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "no RoomDTO found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
        System.out.println(roomPrice);
        netAmount= Double.valueOf(lblAmount.getText());
        lblAmount.setText(String.valueOf(netAmount+(roomPrice * roomdays)));
    }

    public void btnPrintBillOnaction(ActionEvent actionEvent) throws SQLException {

        String name = GuestDAOImpl.getName(cmbGuestId.getValue());
        String amount = lblAmount.getText();
        String packageId = MealOrderDetailsDAOImpl.getpkg(cmbOrderId.getValue());
        String items = MealPlansDAOImpl.getItems(packageId);
        String hallId = HallReservationDetailsDAOImpl.getHall(cmbhallReservationId.getValue());
        String roomId = RoomReservationDetailsDAOImpl.getRoom(cmbRoomReservationId.getValue());
        String qty = MealOrderDAOImpl.getQty(cmbOrderId.getValue());
        try {
            InputStream rpt = this.getClass().getResourceAsStream("/reports/payment.jrxml");
            JasperReport compileReport = JasperCompileManager.compileReport(rpt);
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("amount", amount);
            data.put("item", items);
            data.put("hallId", hallId);
            data.put("roomId", roomId);
            data.put("Qty", qty);

            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, data, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(fillReport, false);
            //JasperPrintManager.printReport(fillReport, true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }
}
