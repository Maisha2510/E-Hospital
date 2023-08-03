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
import java.util.ResourceBundle;

public class Doctor_ScheduleController implements Initializable {

    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> DepartmentFxid;

    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> DoctorIDFxid;

    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> DoctorNameFxid;

    @FXML
    private TableView<Doctor_ScheduleTableModel> ScheduleTableFxid;


    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> SerialNoFxid;

    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> TimeFxid;

    @FXML
    private TableColumn<Doctor_ScheduleTableModel, String> VisitingDaysFxid;
    @FXML
    private TextField keywordTextField;


    ObservableList<Doctor_ScheduleTableModel> scheduleList = FXCollections.observableArrayList();




    Connection conn;
    PreparedStatement pst;
    ResultSet rs;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();
search();


    }

    public void search()

    {
        FilteredList<Doctor_ScheduleTableModel> filteredData = new FilteredList(scheduleList, b -> true);

        keywordTextField.textProperty().addListener((observable,oldvalue,newvalue)->{

            filteredData.setPredicate(Doctor_ScheduleTableModel -> {

                if(newvalue.isEmpty() || newvalue==null)
                {
                    return true;
                }

                String searchKeyword = newvalue.toLowerCase();

                if(Doctor_ScheduleTableModel.getDepartmentName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_ScheduleTableModel.getDoctorName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_ScheduleTableModel.getDoctorId().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                return false;




            });

        });

        SortedList<Doctor_ScheduleTableModel> sortData = new SortedList<>(filteredData);
        sortData.comparatorProperty().bind(ScheduleTableFxid.comparatorProperty());

        ScheduleTableFxid.setItems(sortData);

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
            String fetch_query = "select * from Schedule where DoctorId= '\"+sharedname+\"'";
            ResultSet rs = st.executeQuery(fetch_query);

            while (rs.next()) {

                int serialNo = rs.getInt("SerialNo");
                String departmentName = rs.getString("DepartmentName");
                String doctorName = rs.getString("DoctorName");
                String doctorId = rs.getString("DoctorId");
                String visitingDays = rs.getString("VisitingDays");
                String time = rs.getString("Time");

                scheduleList.add(new Doctor_ScheduleTableModel( departmentName, doctorName, doctorId,visitingDays, time, serialNo));
            }
            ScheduleTableFxid.setItems(scheduleList);
        } catch (Exception e) {

        }
    }

    ScheduleTableModel scheduleTableModel1;

    @FXML
    void BackBtn(ActionEvent event) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("DoctorDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }





}
