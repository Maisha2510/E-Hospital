package com.example.ehospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class patientController implements Initializable {

    ObservableList<PatientTableModel> patientList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<PatientTableModel, String> PatientAddressFxid;

    @FXML
    private TextField PatientAddress_TfFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientBloodGroupFxid;

    @FXML
    private ComboBox<String> PatientBlood_CbFxid1;

    @FXML
    private TableColumn<PatientTableModel, String> PatientCreateDateFxid;

    @FXML
    private DatePicker PatientCreateDate_DpFxid;

    @FXML
    private Button PatientDeleteBtn_fxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientDobFxid;

    @FXML
    private DatePicker PatientDob_DtFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientEmailFxid;

    @FXML
    private TextField PatientEmail_TfFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientFirstNameFxid;

    @FXML
    private TextField PatientFirstName_TfFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientGenderFxid;

    @FXML
    private ComboBox<String> PatientGender_TfFxid;

    @FXML
    private Button PatientInsertBtn_fxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientLastNameFxid;

    @FXML
    private TextField PatientLastName_TfFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientMobileFxid;

    @FXML
    private TextField PatientMobile_TfFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientPhoneFxid;

    @FXML
    private TextField PatientPhone_TfFxid;

    @FXML
    private Button PatientResetBtn_fxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientSerialNoFxid;

    @FXML
    private TableColumn<PatientTableModel, String> PatientStatusFxid;

    @FXML
    private ComboBox<String> PatientStatus_CbFxid;

    @FXML
    private TableView<PatientTableModel> PatientTableFxid;

    @FXML
    private Button PatientUpdateBtn_fxid;

    PatientTableModel patient;

    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female", "Neutral");
    ObservableList<String> bloodList = FXCollections.observableArrayList("O+", "O-", "A+", "A+", "B+", "B-", "AB+", "AB-");
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");

    Connection conn;

    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();

        PatientGender_TfFxid.setItems(genderList);
        PatientBlood_CbFxid1.setItems(bloodList);
        PatientStatus_CbFxid.setItems(statusList);
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

                patientList.add(new PatientTableModel(PatientFirstName, PatientLastName, PatientEmail,
                        PatientMobile, PatientPhone, PatientAddress, PatientGender, PatientBloodGroup,
                        PatientDob, PatientCreateDate, PatientStatus,PatientId,PatientSerialNo));
            }
            PatientTableFxid.setItems(patientList);
        } catch (Exception e) {

        }
    }

    public void PatientInsertBtn(ActionEvent actionEvent) throws SQLException {
        PreparedStatement pst = null;
        try {
            String query = "INSERT INTO Patient (FirstName, LastName, EmailAddress,MobileNo,PhoneNo,Address,Gender,BloodGroup,DOB,CreateDate,Status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);
            String firstName = PatientFirstName_TfFxid.getText();
            String lastName = PatientLastName_TfFxid.getText();
            String email = PatientEmail_TfFxid.getText();
            String mobile = PatientMobile_TfFxid.getText();
            String phone = PatientPhone_TfFxid.getText();
            String address = PatientAddress_TfFxid.getText();
            String gender = PatientGender_TfFxid.getSelectionModel().getSelectedItem().toString();
            String bloodGroup = PatientBlood_CbFxid1.getSelectionModel().getSelectedItem().toString();
            String status = PatientStatus_CbFxid.getSelectionModel().getSelectedItem().toString();
            LocalDate dateOfBirth = PatientDob_DtFxid.getValue();
            LocalDate createDate = PatientCreateDate_DpFxid.getValue();


            if (firstName.equals("") || lastName.equals("") || mobile.equals("") || phone.equals("") || address.equals("")) {
                Notifications.create()
                        .title("Warning")
                        .text("Please fillup all the information")
                        .showError();

            } else {
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, email);
                pst.setString(4, mobile);
                pst.setString(5, phone);
                pst.setString(6, address);
                pst.setString(7, gender);
                pst.setString(8, bloodGroup);
                pst.setString(9, status);
                pst.setDate(10, Date.valueOf(dateOfBirth));
                pst.setDate(11, Date.valueOf(createDate));


                pst.executeUpdate();
                Notifications.create()
                        .title("Info")
                        .text("Added Successfully")
                        .show();
                System.out.println("inserted");


            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void PatientUpdateBtn(ActionEvent actionEvent) {
    }

    public void DeleteBtn(ActionEvent actionEvent) {
        PreparedStatement pst = null;
        try {

            patient=PatientTableFxid.getSelectionModel().getSelectedItem();
            String sql="DELETE FROM Patient  WHERE Patient.MobileNo=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,patient.getMobileNo());
            pst.executeUpdate();
            Notifications.create()
                    .title("Info")
                    .text("Deleted Successfully")
                    .show();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void BackBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}

