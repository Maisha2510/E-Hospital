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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.ehospital.MainController.sharedname;

public class Doctor_DoctorController implements Initializable {

    public TextField keywordTextField;
    ObservableList<Doctor_DoctorTableModel> doctorList = FXCollections.observableArrayList();


    @FXML
    private TableView<Doctor_DoctorTableModel> DoctorTableFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> FnameFxid;

    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> LnameFxid;

    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> DepartmentFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> EmailAddressFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> MobileFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> PhoneFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> AddressFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> GenderFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> BGFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> DOBFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> UserRoleFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> JoinDateFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> StatusFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, Integer> FeesFxid;
    @FXML
    private TableColumn<Doctor_DoctorTableModel, String> SerialNoFxid;




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


    public void fetch_info() {
        SerialNoFxid.setCellValueFactory(new PropertyValueFactory<>("SerialNo"));
        FnameFxid.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        LnameFxid.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        DepartmentFxid.setCellValueFactory(new PropertyValueFactory<>("Department"));
        EmailAddressFxid.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        MobileFxid.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
        PhoneFxid.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
        AddressFxid.setCellValueFactory(new PropertyValueFactory<>("Address"));
        GenderFxid.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        BGFxid.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
        DOBFxid.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        JoinDateFxid.setCellValueFactory(new PropertyValueFactory<>("JoinDate"));
        StatusFxid.setCellValueFactory(new PropertyValueFactory<>("Status"));
        FeesFxid.setCellValueFactory(new PropertyValueFactory<>("Fees"));

        try {
            Statement st = conn.createStatement();
            String fetch_query = "select * from DoctorTable  ";
            ResultSet rs = st.executeQuery(fetch_query);

            while (rs.next()) {

                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String department = rs.getString("Department");
                String emailAddress = rs.getString("EmailAddress");
                String mobile = rs.getString("MobileNo");
                String phone = rs.getString("PhoneNo");
                String address = rs.getString("Address");
                String gender = rs.getString("Sex");
                String bloodgroup = rs.getString("BloodGroup");
                String dob = rs.getString("DOB");
                String joindate = rs.getString("JoinDate");
                String status = rs.getString("Status");
                int fees = rs.getInt("Fees");

                int serialNo = rs.getInt("SerialNo");
                doctorList.add(new Doctor_DoctorTableModel( serialNo, firstName, lastName, department, emailAddress, mobile, phone, address, gender, bloodgroup, dob, joindate, status, fees));
            }
            DoctorTableFxid.setItems(doctorList);
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

    public void search()

    {
        FilteredList<Doctor_DoctorTableModel> filteredData = new FilteredList(doctorList, b -> true);

        keywordTextField.textProperty().addListener((observable,oldvalue,newvalue)->{

            filteredData.setPredicate(Doctor_DoctorTableModel -> {

                if(newvalue.isEmpty() || newvalue==null)
                {
                    return true;
                }

                String searchKeyword = newvalue.toLowerCase();

                if(Doctor_DoctorTableModel.getFirstName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_DoctorTableModel.getLastName().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                else if(Doctor_DoctorTableModel.getDepartment().toLowerCase().indexOf(searchKeyword) >-1)
                {
                    return true;
                }
                return false;




            });

        });

        SortedList<Doctor_DoctorTableModel> sortData = new SortedList<>(filteredData);
        sortData.comparatorProperty().bind(DoctorTableFxid.comparatorProperty());

        DoctorTableFxid.setItems(sortData);

    }

}
