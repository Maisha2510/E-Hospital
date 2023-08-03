package com.example.ehospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Doctor_patientController implements Initializable {

    ObservableList<Doctor_PatientTableModel> patientList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientAddressFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientBloodGroupFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientCreateDateFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientDobFxid;
    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientEmailFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientFirstNameFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientGenderFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientLastNameFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientMobileFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientPhoneFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientSerialNoFxid;

    @FXML
    private TableColumn<Doctor_PatientTableModel, String> PatientStatusFxid;

    @FXML
    private TableView<Doctor_PatientTableModel> PatientTableFxid;
    @FXML
    private TextField keywordTextField;


    PatientTableModel patient;
    Connection conn;

    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();
        search();
    }

    public void search()

    {
        FilteredList<Doctor_PatientTableModel> filteredData = new FilteredList(patientList, b -> true);

        keywordTextField.textProperty().addListener((observable,oldvalue,newvalue)->{

            filteredData.setPredicate(Doctor_PatientTableModel -> {

                if(newvalue.isEmpty() || newvalue==null)
                {
                    return true;
                }

                String searchKeyword = newvalue.toLowerCase();

                if(Doctor_PatientTableModel.getFirstName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_PatientTableModel.getLastName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_PatientTableModel.getPatientId().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                return false;




            });

        });

        SortedList<Doctor_PatientTableModel> sortData = new SortedList<>(filteredData);
        sortData.comparatorProperty().bind(PatientTableFxid.comparatorProperty());

        PatientTableFxid.setItems(sortData);

    }

    private void fetch_info() {
        PatientSerialNoFxid.setCellValueFactory(new PropertyValueFactory("SerialNo"));
        PatientFirstNameFxid.setCellValueFactory(new PropertyValueFactory("FirstName"));
        PatientLastNameFxid.setCellValueFactory(new PropertyValueFactory("LastName"));
        PatientEmailFxid.setCellValueFactory(new PropertyValueFactory("EmailAddress"));
        PatientMobileFxid.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
        PatientPhoneFxid.setCellValueFactory(new PropertyValueFactory("PhoneNo"));
        PatientAddressFxid.setCellValueFactory(new PropertyValueFactory("Address"));
        PatientGenderFxid.setCellValueFactory(new PropertyValueFactory("Gender"));
        PatientBloodGroupFxid.setCellValueFactory(new PropertyValueFactory("BloodGroup"));
        PatientDobFxid.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        PatientCreateDateFxid.setCellValueFactory(new PropertyValueFactory("CreateDate"));
        PatientStatusFxid.setCellValueFactory(new PropertyValueFactory<>("Status"));
       // PatientStatusFxid.setCellValueFactory(new PropertyValueFactory<>("PatientId"));

        try {
            Statement st = conn.createStatement();
            String fetch_query = "select * from Patient";
            ResultSet rs = st.executeQuery(fetch_query);

            while (rs.next()) {
                int PatientSerialNo = rs.getInt("SerialNo");
                String PatientFirstName = rs.getString("FirstName");
                String PatientLastName = rs.getString("LastName");
                String PatientEmail = rs.getString("EmailAddress");
                String PatientMobile = rs.getString("MobileNo");
                String PatientPhone = rs.getString("PhoneNo");
                String PatientAddress = rs.getString("Address");
                String PatientGender = rs.getString("Gender");
                String PatientBloodGroup = rs.getString("BloodGroup");
                String PatientDob = rs.getString("DOB");
                String PatientCreateDate = rs.getString("CreateDate");
                String PatientStatus = rs.getString("Status");
                String PatientId = rs.getString("Status");

                patientList.add(new Doctor_PatientTableModel(PatientFirstName, PatientLastName, PatientEmail,
                        PatientMobile, PatientPhone, PatientAddress, PatientGender, PatientBloodGroup,
                        PatientDob, PatientCreateDate, PatientStatus,PatientId,PatientSerialNo));
            }
            PatientTableFxid.setItems(patientList);
        } catch (Exception e) {

        }
    }



    public void BackBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("DoctorDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}

