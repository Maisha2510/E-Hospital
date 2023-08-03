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
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    private TableColumn<ScheduleTableModel, String> DepartmentFxid;

    @FXML
    private TableColumn<ScheduleTableModel, String> DoctorIDFxid;

    @FXML
    private TableColumn<ScheduleTableModel, String> DoctorNameFxid;

    @FXML
    private TableView<ScheduleTableModel> ScheduleTableFxid;

    @FXML
    private ComboBox<String> ScheduleDepartmentCBfxid;

    @FXML
    private ComboBox<String> ScheduleDoctorCBfxid;

    @FXML
    private ComboBox<String> ScheduleDoctorIDCBfxid;

    @FXML
    private TextField ScheduleVisitingHoutTFfxid;

    @FXML
    private TableColumn<ScheduleTableModel, String> SerialNoFxid;

    @FXML
    private TableColumn<ScheduleTableModel, String> TimeFxid;

    @FXML
    private TableColumn<ScheduleTableModel, String> VisitingDaysFxid;

    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private CheckBox checkbox3;

    @FXML
    private CheckBox checkbox4;

    @FXML
    private CheckBox checkbox5;

    @FXML
    private CheckBox checkbox6;

    ObservableList<ScheduleTableModel> scheduleList = FXCollections.observableArrayList();

    ObservableList<String> checkBoxList = FXCollections.observableArrayList();


    Connection conn;
    PreparedStatement pst;
    ResultSet rs;


    final ObservableList departmentData = FXCollections.observableArrayList();
    final ObservableList doctorNameData = FXCollections.observableArrayList();
    final ObservableList doctorIdData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();


        departmentComboBoxFillData();
        ScheduleDepartmentCBfxid.setItems(departmentData);

    }


    //    checkBoxList
   public void checkBox1Action(ActionEvent event) {
        if (checkbox1.isSelected()) {
            checkBoxList.add(checkbox1.getText());
        } else {
            checkBoxList.remove(checkbox1.getText());
        }
    }

    public void checkBox2Action(ActionEvent event) {
        if (checkbox2.isSelected()) {
            checkBoxList.add(checkbox2.getText());
        } else {
            checkBoxList.remove(checkbox2.getText());
        }
    }

    public void checkBox3Action(ActionEvent event) {
        if (checkbox3.isSelected()) {
            checkBoxList.add(checkbox3.getText());
        } else {
            checkBoxList.remove(checkbox3.getText());
        }
    }

    public void checkBox4Action(ActionEvent event) {
        if (checkbox4.isSelected()) {
            checkBoxList.add(checkbox4.getText());
        } else {
            checkBoxList.remove(checkbox4.getText());
        }
    }

    public void checkBox5Action(ActionEvent event) {
        if (checkbox5.isSelected()) {
            checkBoxList.add(checkbox5.getText());
        } else {
            checkBoxList.remove(checkbox5.getText());
        }
    }

    public void checkBox6Action(ActionEvent event) {
        if (checkbox6.isSelected()) {
            checkBoxList.add(checkbox6.getText());
        } else {
            checkBoxList.remove(checkbox6.getText());
        }
    }

    public void fetch_info() {
        SerialNoFxid.setCellValueFactory(new PropertyValueFactory<>("SerialNo"));
        DepartmentFxid.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));
        DoctorNameFxid.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        DoctorIDFxid.setCellValueFactory(new PropertyValueFactory<>("DoctorId"));
        VisitingDaysFxid.setCellValueFactory(new PropertyValueFactory<>("VisitingDays"));
        TimeFxid.setCellValueFactory(new PropertyValueFactory<>("Time"));



        try {
            Statement st = conn.createStatement();
            String fetch_query = "select * from Schedule";
            ResultSet rs = st.executeQuery(fetch_query);

            while (rs.next()) {

                int serialNo = rs.getInt("SerialNo");
                String departmentName = rs.getString("DepartmentName");
                String doctorName = rs.getString("DoctorName");
                String doctorId = rs.getString("DoctorId");
                String visitingDays = rs.getString("VisitingDays");
                String time = rs.getString("Time");

                scheduleList.add(new ScheduleTableModel( departmentName, doctorName, doctorId,visitingDays, time, serialNo));
            }
            ScheduleTableFxid.setItems(scheduleList);
        } catch (Exception e) {

        }
    }

    ScheduleTableModel scheduleTableModel1;

    public void departmentComboBoxFillData() {
        departmentData.clear();
        String query = " select DepartmentName from Department";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                departmentData.add(rs.getString("DepartmentName"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void departmentCBAction(ActionEvent event) {
        String selectedItem = ScheduleDepartmentCBfxid.getSelectionModel().getSelectedItem().toString();
        doctorNameComboBoxFillData(selectedItem);
    }

    public void doctorNameComboBoxFillData(String selectedItem) {
        doctorNameData.clear();
        String query = "select FullName from DoctorTable where " + "Department = '" + selectedItem + "'";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                doctorNameData.add(rs.getString("Fullname"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in fetching data from database, cz no data selected in prev combo box");
        }
        ScheduleDoctorCBfxid.setItems(doctorNameData);
    }
    public void doctorNameCBAction(ActionEvent event) {
        String selectedItem = ScheduleDoctorCBfxid.getSelectionModel().getSelectedItem().toString();
        doctorIdComboBoxFillData(selectedItem);
    }

    public void doctorIdComboBoxFillData(String selectedItem) {

        doctorIdData.clear();
        String query = "select DoctorId from DoctorTable where " + "FullName = '" + selectedItem + "'";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                doctorIdData.add(rs.getString("DoctorId"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ScheduleDoctorIDCBfxid.setItems(doctorIdData);
    }

    @FXML
    void BackBtn(ActionEvent event) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    void ScheduleDeleteBtn(ActionEvent event) {
        PreparedStatement pst = null;
        Connection con;
        try {

            scheduleTableModel1 = ScheduleTableFxid.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM Schedule  WHERE Schedule.DepartmentName=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, scheduleTableModel1.getDepartmentName());
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
    void ScheduleInsertBtn(ActionEvent event) {
        PreparedStatement pst = null;
        try {
            String query = " INSERT INTO Schedule (DepartmentName,DoctorName,DoctorId,VisitingDays,Time) VALUES(?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            String scheduleDept = ScheduleDepartmentCBfxid.getSelectionModel().getSelectedItem().toString();
            String doctorName = ScheduleDoctorCBfxid.getSelectionModel().getSelectedItem().toString();
            String doctorID = ScheduleDoctorIDCBfxid.getSelectionModel().getSelectedItem().toString();
            String scheduleVisitingTime = ScheduleVisitingHoutTFfxid.getText();



            if (ScheduleVisitingHoutTFfxid.equals("")) {
                Notifications.create()
                        .title("Warning")
                        .text("Please fillup all the information")
                        .showError();

            } else {

                pst.setString(1, scheduleDept);
                pst.setString(2, doctorName);
                pst.setString(3, doctorID);
                pst.setString(4, checkBoxList.toString());
                pst.setString(5, scheduleVisitingTime);

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
    void ScheduleRefreshBtn(ActionEvent event) {
        scheduleList.clear();
        fetch_info();
    }

    @FXML
    void ScheduleResetBtn(ActionEvent event) {

    }

    @FXML
    void ScheduleUpdateBtn(ActionEvent event) {

    }

}
