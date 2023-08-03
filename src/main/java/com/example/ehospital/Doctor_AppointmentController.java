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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Doctor_AppointmentController implements Initializable {

    public TextField keywordTextField;
    ObservableList<AppoinmentTableModel> appointmentList = FXCollections.observableArrayList();


    @FXML
    private TableView<AppoinmentTableModel> AppointmentTableFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentDateFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentDepartmentFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentDoctorIdFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentDoctortNameFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentPatientIdFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentProblemDesFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentTimeFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmentserialNoFxid;

    @FXML
    private TableColumn<AppoinmentTableModel, String> AppointmnetIdFxid;




    Connection conn;
    PreparedStatement pst;
    ResultSet rs;


    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();

    }







    private void fetch_info() {
        AppointmentserialNoFxid.setCellValueFactory(new PropertyValueFactory<>("SerialNo"));
        AppointmentDateFxid.setCellValueFactory(new PropertyValueFactory<>("AppointmentDate"));
        AppointmentTimeFxid.setCellValueFactory(new PropertyValueFactory<>("AppointmentTime"));
        AppointmentProblemDesFxid.setCellValueFactory(new PropertyValueFactory<>("ProblemDescription"));
        AppointmentPatientIdFxid.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        AppointmentDepartmentFxid.setCellValueFactory(new PropertyValueFactory<>("Department"));
        AppointmentDoctorIdFxid.setCellValueFactory(new PropertyValueFactory<>("DoctorId"));
        AppointmentDoctortNameFxid.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        AppointmnetIdFxid.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));

        try {
            Statement st = conn.createStatement();
            String fetch_query = "select * from Appointment where DoctorId= '\"+sharedname+\"'";
            ResultSet rs = st.executeQuery(fetch_query);

            while (rs.next()) {

                String appointmentDate = rs.getString("AppointmentDate");
                String appointmentTime = rs.getString("AppointmentTime");
                String probDes = rs.getString("ProblemDescription");
                String patientId = rs.getString("PatientId");
                String dept = rs.getString("Department");
                String appoinmentDate = rs.getString("DoctorId");
                String doctId = rs.getString("DoctorName");
                String appointmentId = rs.getString("AppointmentId");

                appointmentList.add(new AppoinmentTableModel(appointmentDate, appointmentTime, probDes, patientId, dept, appoinmentDate, doctId, appointmentId));
            }
            AppointmentTableFxid.setItems(appointmentList);
        } catch (Exception e) {

        }
    }


    @FXML
    void AppointmentBackBTN(ActionEvent event) throws SQLException, IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("DoctorDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

}


