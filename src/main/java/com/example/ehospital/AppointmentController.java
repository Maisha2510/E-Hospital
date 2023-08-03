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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    ObservableList<AppoinmentTableModel> appointmentList = FXCollections.observableArrayList();
    final ObservableList ApnDept = FXCollections.observableArrayList();
    final ObservableList ApnDoctName = FXCollections.observableArrayList();
    final ObservableList ApnDoctId = FXCollections.observableArrayList();

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


    @FXML
    private DatePicker ApnAppoinmentDate_tf;

    @FXML
    private TextField ApnAppoinmentTime_tf;

    @FXML
    private ComboBox<String> ApnDepartment_tf;

    @FXML
    private ComboBox<String> ApnDoctorID_tf;

    @FXML
    private ComboBox<String> ApnDoctorName_tf1;

    @FXML
    private TextField ApnPatientID_tf;

    @FXML
    private TextArea ApnProbDes_tf;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;


    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();


        fillDeptComboBox();
        ApnDepartment_tf.setItems(ApnDept);

    }

    public void fillDeptComboBox() {
        ApnDept.clear();

        String query = " select DepartmentName from Department";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ApnDept.add(rs.getString("DepartmentName"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectDeptOnActionBTN(ActionEvent event) {
        String selectedItem = ApnDepartment_tf.getSelectionModel().getSelectedItem().toString();
        fillDoctNameComboBox(selectedItem);

    }

    public void fillDoctNameComboBox(String selectedItem) {
        ApnDoctName.clear();

        String query = "select FullName from DoctorTable where " + "Department = '" + selectedItem + "'";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ApnDoctName.add(rs.getString("FullName"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ApnDoctorName_tf1.setItems(ApnDoctName);
    }

    public void selectDoctNameOnActionBTN(ActionEvent event) {
        String selectedItem = ApnDoctorName_tf1.getSelectionModel().getSelectedItem().toString();
        fillDoctIDComboBox(selectedItem);

    }


    public void fillDoctIDComboBox(String selectedItem) {
        ApnDoctId.clear();

        String query = "select DoctorId from DoctorTable where " + "FullName = '" + selectedItem + "'";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ApnDoctId.add(rs.getString("DoctorId"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ApnDoctorID_tf.setItems(ApnDoctId);
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
            String fetch_query = "select * from Appointment";
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

        Parent root1 = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    AppoinmentTableModel apnt;

    @FXML
    void AppointmentDeleteBTN(ActionEvent event) {

        PreparedStatement pst = null;
        Connection con;
        try {

            apnt = AppointmentTableFxid.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM Appointment  WHERE Appointment.AppointmentId=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, apnt.getAppointmentId());
            pst.executeUpdate();
            Notifications.create()
                    .title("Info")
                    .text("Deleted Successfully")
                    .show();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void AppointmentInsertBTN(ActionEvent event) throws SQLException {
        PreparedStatement pst = null;
        try {
            String query = " INSERT INTO Appointment (AppointmentDate,AppointmentTime,ProblemDescription,PatientId,Department,DoctorId,DoctorName) VALUES(?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);
            LocalDate apntDate = ApnAppoinmentDate_tf.getValue();
            String apntTime = ApnAppoinmentTime_tf.getText();
            String apntProbDes = ApnProbDes_tf.getText();
            String apntPatientId = ApnPatientID_tf.getText();
            String apntDept = ApnDepartment_tf.getSelectionModel().getSelectedItem().toString();
            String apntDoctorId = ApnDoctorID_tf.getSelectionModel().getSelectedItem().toString();
            String apntDocName = ApnDoctorName_tf1.getSelectionModel().getSelectedItem().toString();


            if (apntDate.equals("") || apntTime.equals("") || apntProbDes.equals("")
                    || apntPatientId.equals("") || apntDept.equals("") ||
                    apntDoctorId.equals("") || apntDocName.equals("")) {
                Notifications.create()
                        .title("Warning")
                        .text("Please fillup all the information")
                        .showError();

            } else {

                pst.setDate(1, Date.valueOf(apntDate));
                pst.setString(2, apntTime);
                pst.setString(3, apntProbDes);
                pst.setString(4, apntPatientId);
                pst.setString(5, apntDept);
                pst.setString(6, apntDoctorId);
                pst.setString(7, apntDocName);

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

    @FXML
    void AppointmentResetBTN(ActionEvent event) {


    }

    @FXML
    void AppointmentUpdateBTN(ActionEvent event) {

        PreparedStatement pst = null;
        Connection con;
        try {

            LocalDate apntDate = ApnAppoinmentDate_tf.getValue();
            String apntTime = ApnAppoinmentTime_tf.getText();
            String apntProbDes = ApnProbDes_tf.getText();
            String apntPatientId = ApnPatientID_tf.getText();
            String apntDept = ApnDepartment_tf.getSelectionModel().getSelectedItem().toString();
            String apntDoctorId = ApnDoctorID_tf.getSelectionModel().getSelectedItem().toString();
            String apntDocName = ApnDoctorName_tf1.getSelectionModel().getSelectedItem().toString();

            String sql = "Update Appointment  set AppointmentDate=  '"+apntDate+"', AppointmentTime= '"+apntTime+"',ProblemDescription= '"+apntProbDes+"', PatientId= '"+apntPatientId+"',Department= '"+apntDept+"', DoctorId= '"+ apntDoctorId+"',AppointmentId= '"+apntDocName+"'' WHERE Appointment.DoctorName= '"+apntDocName+"'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            Notifications.create()
                    .title("Info")
                    .text("Updated Successfully")
                    .show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void AppointmentRefreshBTN(ActionEvent event) {
        appointmentList.clear();
        fetch_info();
    }
}


