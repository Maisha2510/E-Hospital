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
import java.util.Random;
import java.util.ResourceBundle;

public class BillingController implements Initializable {
    ObservableList<BillingTableModel> BillList = FXCollections.observableArrayList();
    @FXML
    public TextField PatientFxid;
    @FXML
    public TextField AdditionalBillFxid;
    @FXML
    public TextField DiscountFxid;

    @FXML
    public ComboBox<String> CurrentFxid;

    @FXML
    private ComboBox<String> AppFxid ;

    @FXML
    public DatePicker DateFxid;
public  String pts;
    @FXML
    private TableView<BillingTableModel> BillingTableFxid;
    @FXML
    private TableColumn<BillingTableModel, String> BillingColumnSerialNoFxid;

    @FXML
    private TableColumn<BillingTableModel, String> PatientIdFxid;

    @FXML
    private TableColumn<BillingTableModel, String> BilldateFxid;

    @FXML
    private TableColumn<BillingTableModel, String> AppointmentIDFxid;

    @FXML
    private TableColumn<BillingTableModel, String> BillIdIdFxid;
    @FXML
    private TableColumn<BillingTableModel, String> AdditionalFxid;

    @FXML
    private TableColumn<BillingTableModel, String> DisFxid;

    @FXML
    private TableColumn<BillingTableModel, String> TotalFxid;

    @FXML
    private TableColumn<BillingTableModel, String>  StatusFxid;
    BillingTableModel dept;
    final ObservableList ApnId = FXCollections.observableArrayList();

    Connection conn;
    int index=-1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnect.Connection();
        conn = DatabaseConnect.con;
        fetch_info();
        ObservableList<String> list = FXCollections.observableArrayList("Active","Inactive");
        CurrentFxid.setItems(list);


    }
    public void filldata(String pt) {
        ApnId.clear();
        PreparedStatement pst=null;
        ResultSet rs;


        String query = " select AppointmentID from Appointment where PatientId='"+pt+"' ";

        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ApnId.add(rs.getString("AppointmentID"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AppFxid.setItems(ApnId);

    }

    public void fetch_info() {

        BillingColumnSerialNoFxid.setCellValueFactory(new PropertyValueFactory("SerialNo"));
        PatientIdFxid.setCellValueFactory(new PropertyValueFactory("PatientId"));
        BilldateFxid.setCellValueFactory(new PropertyValueFactory("BillDate"));
        AppointmentIDFxid.setCellValueFactory(new PropertyValueFactory("AppointmentID"));
        BillIdIdFxid.setCellValueFactory(new PropertyValueFactory<>("BillId"));
        AdditionalFxid.setCellValueFactory(new PropertyValueFactory("AdditionalBill"));
        DisFxid.setCellValueFactory(new PropertyValueFactory("Discount"));
        TotalFxid.setCellValueFactory(new PropertyValueFactory("Total"));
        StatusFxid.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            Statement st1 = conn.createStatement();
            String fetch_query2 = "select * from Billing";
            ResultSet rs2 = st1.executeQuery(fetch_query2);

            while (rs2.next()) {

                String patientid = rs2.getString("PatientId");
                String date = rs2.getString("BillDate");
                String appointid = rs2.getString("AppointmentID");
                String status = rs2.getString("Status");
                int serialNo = rs2.getInt("SerialNo");
                int total=rs2.getInt("Total");
                int discount=rs2.getInt("Discount");
                int addbill=rs2.getInt("AdditionalBill");
                String billid=rs2.getString("BillId");
                BillList.add(new BillingTableModel(appointid, date, status, patientid,billid, serialNo,discount,total,addbill));
            }
            BillingTableFxid.setItems(BillList);
        } catch (Exception e) {

        }

    }

    public void InsertBtn(ActionEvent actionEvent)  throws SQLException {
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3= null;
        try {
            String query = " INSERT INTO Billing ( PatientId,BillDate,BillId,AdditionalBill,Discount,Status,AppointmentID) VALUES(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            String ptid=PatientFxid.getText();
            String star= AppFxid.getSelectionModel().getSelectedItem().toString();
            LocalDate bd=DateFxid.getValue();
            String disc= DiscountFxid.getText();
            int ds=Integer.parseInt(disc);
            String adb= AdditionalBillFxid.getText();
            int addbi=Integer.parseInt(adb);
            String sta= CurrentFxid.getSelectionModel().getSelectedItem().toString();
            String alpha="ABCDEFGHIJKLMNOPQRST123456789";
            StringBuilder sb=new StringBuilder();
            Random random=new Random();
            int  len=8;
            for(int i=0;i<len;i++)
            {
                int ind= random.nextInt(alpha.length());
                char ranch=alpha.charAt(ind);
                sb.append(ranch);
            }
            String blid= sb.toString();

            if(ptid.equals("") || bd.equals("")||star.equals("")||sta.equals("")||disc.equals("")||adb.equals(""))
            {
                Notifications.create()
                        .title("Warning")
                        .text("Please fillup all the information")
                        .showError();

            }
            else
            {

                pst.setString(1, ptid);
                pst.setDate(2, Date.valueOf(bd));
                pst.setString(3,blid);
                pst.setInt(4,addbi);
                pst.setInt(5,ds);
                pst.setString(6,sta);
                pst.setString(7,star);
                pst.executeUpdate();
                ResultSet rs = conn.createStatement().executeQuery(   " SELECT  Appointment.AppointmentID as AppointmentID2 ,Appointment.DoctorId as doc  FROM Appointment,Billing WHERE  Billing.PatientId=Appointment.PatientId and Billing.BillDate=Appointment.AppointmentDate  ");
                String apid=null;
                String docid=null;
                while (rs.next())
                {
                    apid=rs.getString("AppointmentID2");
                    docid=rs.getString("doc");

                }
                ResultSet rs2 = conn.createStatement().executeQuery(   " SELECT   DoctorTable.Fees as docfee FROM DoctorTable where DoctorTable.DoctorId= '"+docid+"' " );
                String docff=null;
                while (rs2.next())
                {

                    docff=rs2.getString("docfee");
                }
                int afe=Integer.parseInt(docff);
                double total=afe+addbi;
                double ds2=((double)ds)/100;
                System.out.println(ds2);
                double totals=(total*ds2);
                int totalfinal=(int) (total-totals);
                System.out.println(totalfinal);
                String query2 = " UPDATE Billing set Total= '"+totalfinal+"' WHERE  Billing.PatientId='"+ptid+"' and Billing.BillDate='"+bd+"'  ";
                pst2 = conn.prepareStatement(query2);
                pst2.executeUpdate();



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


    public void UpdateBtn(ActionEvent actionEvent) {
//        PreparedStatement pst2 = null;
//        Connection conn;
//        try {
//
//
//            String depname=Deptnamefxid.getText();
//            String des= Des_fxid.getText();
//            String sta= Status_fxid.getText();
//            String sql = "Update Department  set DepartmentName=  '"+depname+"', Description= '"+des+"',Status= '"+sta+"' WHERE Department.DepartmentName= '"+depname+"'";
//            pst = conn.prepareStatement(sql);
//            pst.executeUpdate();
//
//            Notifications.create()
//                    .title("Info")
//                    .text("Updated Successfully")
//                    .show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void DeleteBtn(ActionEvent actionEvent) {
        PreparedStatement pst = null;
        Connection con;
        try {

            dept=BillingTableFxid.getSelectionModel().getSelectedItem();
            String sql="DELETE FROM Billing  WHERE Billing.BillId=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,dept.getBillId());
            pst.executeUpdate();
            Notifications.create()
                    .title("Info")
                    .text("Deleted Successfully")
                    .show();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void ResetBtn(ActionEvent actionEvent) throws SQLException {

        BillList.clear();
        fetch_info();



    }

    public void SelectedBtn(MouseEvent mouseEvent) {

//        index=BillingTableFxid.getSelectionModel().getSelectedIndex();
//        if(index<=-1)
//        {
//            return;
//        }
//        PatientFxid.setText(PatientIdFxid.getCellData(index).toString());
//        AdditionalBillFxid.setText(AdditionalFxid.getCellData(index).toString());
//        DiscountFxid.setText(DisFxid.getCellData(index).toString());
//        DateFxid.show(BilldateFxid.getCellData(index).toString());
//        Status_fxid.setText(StatusFxid.getCellData(index).toString());
//        CurrentFxid.Add(StatusFxid.getCellData(index).toString());
//        CurrentFxid.
//



    }
    public void BackBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public void SelectItem(ActionEvent actionEvent) {


    }

    public void sendDataPatientId(ActionEvent actionEvent) {
        String p=PatientFxid.getText();
        filldata(p);
    }
}
