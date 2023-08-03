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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    ObservableList<DoctorTableModel> doctorList = FXCollections.observableArrayList();
    final ObservableList dept = FXCollections.observableArrayList();


    @FXML
    private TableView<DoctorTableModel> DoctorTableFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> FnameFxid;

    @FXML
    private TableColumn<DoctorTableModel, String> LnameFxid;

    @FXML
    private TableColumn<DoctorTableModel, String> DepartmentFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> EmailAddressFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> MobileFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> PhoneFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> AddressFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> GenderFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> BGFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> DOBFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> UserRoleFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> JoinDateFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> StatusFxid;
    @FXML
    private TableColumn<DoctorTableModel, Integer> FeesFxid;
    @FXML
    private TableColumn<DoctorTableModel, String> SerialNoFxid;

    @FXML
    private ComboBox<String> DoctorDept_fxid;

    @FXML
    private TextField DoctorAddress_fxid;

    @FXML
    private ComboBox<String> DoctorBG_fxid1;

    @FXML
    private DatePicker DoctorDob_fxid;

    @FXML
    private TextField DoctorEmail_fxid;

    @FXML
    private TextField DoctorFee_fxid;

    @FXML
    private TextField DoctorFname_fxid;

    @FXML
    private ComboBox<String> DoctorGender_fxid;

    @FXML
    private DatePicker DoctorJoin_fxid;

    @FXML
    private TextField DoctorLname_fxid;

    @FXML
    private TextField DoctorMobile_fxid;

    @FXML
    private TextField DoctorPhone_fxid;

    @FXML
    private ComboBox<String> DoctorStatus_fxid;

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public void fillComboBox() {
        dept.clear();
        String query = " select DepartmentName from Department";
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                dept.add(rs.getString("DepartmentName"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();

        ObservableList<String> bgcomb = FXCollections.observableArrayList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        DoctorBG_fxid1.setItems(bgcomb);

        ObservableList<String> gendercomb = FXCollections.observableArrayList("Male", "Female");
        DoctorGender_fxid.setItems(gendercomb);

        ObservableList<String> statuscomb = FXCollections.observableArrayList("Active", "Inactive");
        DoctorStatus_fxid.setItems(statuscomb);
        fillComboBox();
        DoctorDept_fxid.setItems(dept);


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
            String fetch_query = "select * from DoctorTable";
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
                doctorList.add(new DoctorTableModel( serialNo, firstName, lastName, department, emailAddress, mobile, phone, address, gender, bloodgroup, dob, joindate, status, fees));
            }
            DoctorTableFxid.setItems(doctorList);
        } catch (Exception e) {

        }
    }

    DoctorTableModel doct;

    @FXML
    void DoctorDeleteBtn(ActionEvent event) {
        PreparedStatement pst = null;
        Connection con;
        try {

            doct = DoctorTableFxid.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM DoctorTable  WHERE DoctorTable.FirstName=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, doct.getFirstName());
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
    void DoctorInsertBtn(ActionEvent event) throws SQLException {

        PreparedStatement pst = null;
        try {
            String query = " INSERT INTO DoctorTable (FirstName,LastName,Department,EmailAddress,MobileNo,PhoneNo,Address,Sex,BloodGroup,DOB,JoinDate,Status,Fees) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);
            String doctorFname = DoctorFname_fxid.getText();
            String doctorLname = DoctorLname_fxid.getText();
            String doctorDept = DoctorDept_fxid.getSelectionModel().getSelectedItem().toString();
            String doctorEmail = DoctorEmail_fxid.getText();
            String doctorMobile = DoctorMobile_fxid.getText();
            String doctorPhone = DoctorPhone_fxid.getText();
            String doctorAddress = DoctorAddress_fxid.getText();
            String doctorFee = DoctorFee_fxid.getText();
            LocalDate doctorDob = DoctorDob_fxid.getValue();
            String doctorBG = DoctorBG_fxid1.getSelectionModel().getSelectedItem().toString();
            String doctorGender = DoctorGender_fxid.getSelectionModel().getSelectedItem().toString();
            LocalDate doctorJoin = DoctorJoin_fxid.getValue();
            String doctorStatus = DoctorStatus_fxid.getSelectionModel().getSelectedItem().toString();

            if (doctorFname.equals("") || doctorLname.equals("") || doctorEmail.equals("") || doctorMobile.equals("") ||
                    doctorPhone.equals("") || doctorAddress.equals("") || doctorFee.equals("") || doctorDob.equals("") ||
                    doctorBG.equals("") || doctorGender.equals("")) {
                Notifications.create()
                        .title("Warning")
                        .text("Please fillup all the information")
                        .showError();

            } else {

                pst.setString(1, doctorFname);
                pst.setString(2, doctorLname);
                pst.setString(3, doctorDept);
                pst.setString(4, doctorEmail);
                pst.setString(5, doctorMobile);
                pst.setString(6, doctorPhone);
                pst.setString(7, doctorAddress);
                pst.setString(8, doctorGender);
                pst.setString(9, doctorBG);
                pst.setDate(10, Date.valueOf(doctorDob));
                pst.setDate(11, Date.valueOf(doctorJoin));
                pst.setString(12, doctorStatus);
                pst.setString(13, doctorFee);
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
        fillComboBox();
    }

    @FXML
    void DoctorRefreshBtn(ActionEvent event) {
        doctorList.clear();
        fetch_info();


    }

    @FXML
    void DoctorResetBtn(ActionEvent event) {

    }

    @FXML
    public void DoctorUpdateBtn(ActionEvent actionEvent) {
        PreparedStatement pst = null;
        Connection con;
        try {


            String DoctorFname_fxid_query=DoctorFname_fxid.getText();
            String DoctorLname_fxid_query= DoctorLname_fxid.getText();
            String DoctorDept_fxid_query= DoctorDept_fxid.getSelectionModel().getSelectedItem().toString();
            String DoctorEmail_fxid_query=DoctorEmail_fxid.getText();
            String DoctorMobile_fxid_query=DoctorMobile_fxid.getText();
            String DoctorPhone_fxid_query=DoctorPhone_fxid.getText();
            String DoctorAddress_fxid_query=DoctorAddress_fxid.getText();
            String DoctorFee_fxid_query=DoctorFee_fxid.getText();
            String DoctorDob_fxid_query=DoctorDob_fxid.getValue().toString();
            String DoctorBG_fxid1_query=DoctorBG_fxid1.getSelectionModel().getSelectedItem().toString();
            String DoctorGender_fxid_query=DoctorGender_fxid.getSelectionModel().getSelectedItem().toString();
            String DoctorJoin_fxid_query=DoctorJoin_fxid.getValue().toString();
            String DoctorStatus_fxid_query=DoctorStatus_fxid.getSelectionModel().getSelectedItem().toString();

            String sql = "Update DoctorTable  set FirstName=  '"+DoctorFname_fxid_query+"', LastName= '"+DoctorLname_fxid_query+"',Department= '"+DoctorDept_fxid_query+"',EmailAddress= '"+DoctorEmail_fxid_query+"',MobileNo= '"+DoctorMobile_fxid_query+"',PhoneNo= '"+DoctorPhone_fxid_query+"',Address= '"+DoctorAddress_fxid_query+"',Fees= '"+DoctorFee_fxid_query+"',DOB= '"+DoctorDob_fxid_query+"',BloodGroup= '"+DoctorBG_fxid1_query+"',Sex= '"+DoctorGender_fxid_query+"',JoinDate= '"+DoctorJoin_fxid_query+"',Status= '"+DoctorStatus_fxid_query+"' WHERE DoctorTable.MobileNo= '"+DoctorMobile_fxid_query+"'";
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
    int index=-1;
    public void SelectedBtn(MouseEvent mouseEvent) {
        index=DoctorTableFxid.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        DoctorFname_fxid.setText(FnameFxid.getCellData(index).toString());
        DoctorLname_fxid.setText(LnameFxid.getCellData(index).toString());
      //  DoctorDept_fxid.setItems(StatusFxid.getCellData(index).toString());
        DoctorEmail_fxid.setText(EmailAddressFxid.getCellData(index).toString());
        DoctorMobile_fxid.setText(MobileFxid.getCellData(index).toString());
        DoctorPhone_fxid.setText(PhoneFxid.getCellData(index).toString());
        DoctorAddress_fxid.setText(AddressFxid.getCellData(index).toString());
        DoctorFee_fxid.setText(FeesFxid.getCellData(index).toString());
       // DoctorDob_fxid.setText(StatusFxid.getCellData(index).toString());
       // DoctorBG_fxid1.setText(StatusFxid.getCellData(index).toString());
//        DoctorGender_fxid.setText(StatusFxid.getCellData(index).toString());
//        DoctorJoin_fxid.setText(StatusFxid.getCellData(index).toString());
//        DoctorStatus_fxid.setText(StatusFxid.getCellData(index).toString());
    }
    public void BackBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

}
