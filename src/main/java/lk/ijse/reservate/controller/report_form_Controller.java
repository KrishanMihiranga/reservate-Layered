package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.db.DBConnection;
import lk.ijse.reservate.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.reservate.dao.custom.impl.GuestDAOImpl;
import lk.ijse.reservate.dao.custom.impl.UserDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class report_form_Controller {
    @FXML
    private AnchorPane reservationPane;

    @FXML
    private JFXButton btnEmployeeDetails;

    @FXML
    private JFXButton btnUserDetails;

    @FXML
    private JFXButton btnRoomDetails;

    @FXML
    private JFXButton btnHallDetails;

    @FXML
    private JFXButton btnMealDetails;
    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXButton btnGenerateReport;
    @FXML
    private JFXButton btnGuestDetails;

    @FXML
    void btnEmployeeDetailsOnAction(ActionEvent event) {

        try{
            List<String> eIds = EmployeeDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String empIds : eIds){
                obList.add(empIds);
            }
            cmbEmpId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    @FXML
    void btnHallDetailsOnAction(ActionEvent event) {
        try {
            InputStream rpt= this.getClass().getResourceAsStream("/reports/HallDetails.jrxml");
            JasperReport compileReport= JasperCompileManager.compileReport(rpt);
            JasperPrint fillReport= JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(fillReport, false);
            //JasperPrintManager.printReport(fillReport, true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMealDetailsOnAction(ActionEvent event) {
        try {
            InputStream rpt= this.getClass().getResourceAsStream("/reports/Mealdetails.jrxml");
            JasperReport compileReport= JasperCompileManager.compileReport(rpt);
            JasperPrint fillReport= JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(fillReport, false);
            //JasperPrintManager.printReport(fillReport, true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnRoomDetailsOnAction(ActionEvent event) {
        try {
            InputStream rpt= this.getClass().getResourceAsStream("/reports/roomDetails.jrxml");
            JasperReport compileReport= JasperCompileManager.compileReport(rpt);
            JasperPrint fillReport= JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(fillReport, false);
            //JasperPrintManager.printReport(fillReport, true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUserDetailsOnAction(ActionEvent event) {
        try{
            List<String> uIds = UserDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String userIds : uIds){
                obList.add(userIds);
            }
            cmbEmpId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }
    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {

    }
    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {
        String fL = null;
        String selectedid = cmbEmpId.getValue();
        cmbEmpId.getItems().clear();
        if (selectedid != null && !selectedid.isEmpty()) {
            fL = String.valueOf(selectedid.charAt(0));
        }
        if (fL.equals("E")) {
            try {
                InputStream rpt = this.getClass().getResourceAsStream("/reports/employeeDetail.jrxml");
                JasperReport compileReport = JasperCompileManager.compileReport(rpt);
                Map<String, Object> data = new HashMap<>();
                data.put("EmpId", selectedid);
                //data.put("name", "test");

                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, data, DBConnection.getInstance().getConnection());
                JasperViewer.viewReport(fillReport, false);
                //JasperPrintManager.printReport(fillReport, true);
            } catch (JRException | SQLException e) {
                e.printStackTrace();
            }
        }else if (fL.equals("U")) {
            try {
                InputStream rpt = this.getClass().getResourceAsStream("/reports/UserDetails.jrxml");
                JasperReport compileReport = JasperCompileManager.compileReport(rpt);
                Map<String, Object> data = new HashMap<>();
                data.put("userId", selectedid);
                //data.put("name", "test");

                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, data, DBConnection.getInstance().getConnection());
                JasperViewer.viewReport(fillReport, false);
                //JasperPrintManager.printReport(fillReport, true);
            } catch (JRException | SQLException e) {
                e.printStackTrace();
            }
        }else if (fL.equals("G")) {
            try {
                InputStream rpt = this.getClass().getResourceAsStream("/reports/GuestDetails.jrxml");
                JasperReport compileReport = JasperCompileManager.compileReport(rpt);
                Map<String, Object> data = new HashMap<>();
                data.put("gId", selectedid);
                //data.put("name", "test");

                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, data, DBConnection.getInstance().getConnection());
                JasperViewer.viewReport(fillReport, false);
                //JasperPrintManager.printReport(fillReport, true);
            } catch (JRException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnGuestDetailsOnAction(ActionEvent event){
        try{
            List<String> uIds = GuestDAOImpl.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for(String userIds : uIds){
                obList.add(userIds);
            }
            cmbEmpId.setItems(obList);
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
}
