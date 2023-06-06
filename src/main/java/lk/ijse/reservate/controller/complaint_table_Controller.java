package lk.ijse.reservate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.reservate.dto.ComplaintDTO;
import lk.ijse.reservate.entity.Complaint;
import lk.ijse.reservate.dao.custom.impl.complaintDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class complaint_table_Controller {

    @FXML
    private TableView<Complaint> tblComplaints;

    @FXML
    private TableColumn<?, ?> colComplaintid;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> coltime;

    @FXML
    private TableColumn<?, ?> colguestid;

    @FXML
    private TableColumn<?, ?> colmealorderid;

    @FXML
    private TableColumn<?, ?> colhallreservationid;

    @FXML
    private TableColumn<?, ?> colroomreservationid;

    @FXML
    private TableColumn<?, ?> coldescription;

    public void initialize(){
        setCellValueFactory();
        getAll();
    }

    private void getAll() {
        try {
            ObservableList<Complaint> obList = FXCollections.observableArrayList();

            List<ComplaintDTO> hresList = complaintDAOImpl.getAll();


            for(ComplaintDTO complaintDTO : hresList) {
                obList.add(new Complaint(
                        complaintDTO.getComplaintId(),
                        complaintDTO.getDate(),
                        complaintDTO.getTime(),
                        complaintDTO.getGuestid(),
                        complaintDTO.getMealorderid(),
                        complaintDTO.getHallreservationid(),
                        complaintDTO.getRoomreservationid(),
                        complaintDTO.getDescription()

                ));
            }
            tblComplaints.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    private void setCellValueFactory() {
        colComplaintid.setCellValueFactory(new PropertyValueFactory<>("ComplaintID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colguestid.setCellValueFactory(new PropertyValueFactory<>("guestid"));
        colmealorderid.setCellValueFactory(new PropertyValueFactory<>("mealorderid"));
        colhallreservationid.setCellValueFactory(new PropertyValueFactory<>("hallreservationid"));
        colroomreservationid.setCellValueFactory(new PropertyValueFactory<>("roomreservationid"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

}
