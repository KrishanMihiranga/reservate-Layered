package lk.ijse.reservate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.reservate.dto.MealPlansDTO;
import lk.ijse.reservate.dao.custom.impl.MealPlansDAOImpl;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class meal_plans_form_Controller {

    @FXML
    private AnchorPane reservationPane;
    @FXML
    private JFXButton btnAddMeal;

    @FXML
    private JFXTextField txtPackageId;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXButton btnUpdateMeal;

    @FXML
    private JFXComboBox<String> cmbMealType;
    @FXML
    private JFXComboBox<String> cmbMealplan;

    @FXML
    private JFXButton btnRemove;
    public void initialize(){
     setType();
     generateNextId();
    }

    private void setType() {
        ObservableList<String> type = FXCollections.observableArrayList(
                "Breakfast",
                "Lunch",
                "Dinner"
        );
        ObservableList<String> plan = FXCollections.observableArrayList(
                "Indian",
                "Srilankan"
        );
        cmbMealplan.setItems(plan);
        cmbMealType.setItems(type);
    }

    private void generateNextId() {
        try {
            String nextId = MealPlansDAOImpl.generateNextId();
            txtPackageId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnAddMealOnAction(ActionEvent event) {
        String PackageId    =txtPackageId.getText();
        String MealPlan     =cmbMealplan.getValue();
        String MealType     =cmbMealType.getValue();
        String Description  =txtDescription.getText();
        String Price        =txtPrice.getText();

        boolean isMatch = Pattern.compile("^\\d+(\\.\\d{1,2})?$").matcher(Price).matches();
        if (isMatch){
            txtPrice.setStyle("-fx-text-fill: black");
            if (PackageId.isEmpty() || MealPlan.isEmpty() || MealType.isEmpty() || Description.isEmpty() || Price.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = MealPlansDAOImpl.save(PackageId, MealPlan, MealType, Description, Price);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Meal Added!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtPrice.setStyle("-fx-text-fill: red");
            txtPrice.setText(Price);
        }

    }

    public void btnupdaeteMealOnAction(ActionEvent actionEvent) {
        String PackageId    =txtPackageId.getText();
        String MealPlan     =cmbMealplan.getValue();
        String MealType     =cmbMealType.getValue();
        String Description  =txtDescription.getText();
        String Price        =txtPrice.getText();

        boolean isMatch = Pattern.compile("^\\d+(\\.\\d{1,2})?$").matcher(Price).matches();
        if (isMatch){
            txtPrice.setStyle("-fx-text-fill: black");
            if (PackageId.isEmpty() || MealPlan.isEmpty() || MealType.isEmpty() || Description.isEmpty() || Price.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Cannot pass empty values!").show();
            }else{
                try{
                    boolean isSaved = MealPlansDAOImpl.update(PackageId, MealPlan, MealType, Description, Price);
                    if(isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Meal Updated!").show();
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Data :/").show();
            txtPrice.setStyle("-fx-text-fill: red");
            txtPrice.setText(Price);
        }


    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        String PackageId    =txtPackageId.getText();

        try{
            boolean isSaved = MealPlansDAOImpl.remove(PackageId);
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void txtPackageIdOnAction(ActionEvent actionEvent) {
        String PackageId    =txtPackageId.getText();

        try {
            MealPlansDTO mealPlansDTO = MealPlansDAOImpl.setFields(PackageId);
            if (mealPlansDTO != null)
            {
                txtPackageId.setText(mealPlansDTO.getPackageId());
                cmbMealplan.setValue(mealPlansDTO.getMealPlan());
                cmbMealType.setValue(mealPlansDTO.getMealType());
                txtDescription.setText(mealPlansDTO.getDescription());
                txtPrice.setText(String.valueOf(mealPlansDTO.getPrice()));


            } else {
                new Alert(Alert.AlertType.WARNING, "no Package found :(").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }
    }
}
