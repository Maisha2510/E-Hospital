package com.example.ehospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainController  implements Initializable {

    @FXML
    public TextField IdField;
    @FXML
  public PasswordField  PasswordField;
    @FXML
    public AnchorPane loginpane;


    @FXML
    public ComboBox  comb;
    public Button SigninBtn;
    PreparedStatement pst = null;
    ResultSet rs;
    public static String sharedname;



    public void SignInAction(ActionEvent actionEvent) {
        System.out.println("Sign in button clicked");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;user=team;password=p@ssword13;databaseName=EHdb";
            Connection con = DriverManager.getConnection(url);
            String uname = IdField.getText();
            String pass =  PasswordField.getText();
            String s = comb.getSelectionModel().getSelectedItem().toString();


            if (uname.equals("") || pass.equals("")||s.equals("")) {
                System.out.println("Please Fill up all the information");


            } else {
                String query = "SELECT * FROM  LoginInfo WHERE UserName=? and Password=? and UserRole=?";


                pst = con.prepareStatement(query);
                pst.setString(1, uname);
                pst.setString(2, pass);
                pst.setString(3, s);
                rs = pst.executeQuery();
                if (rs.next()) {

                    if(s.equals("Admin")) {
                        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                        Stage stage = (Stage)(Window)SigninBtn.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        sharedname=uname;
                    }else if(s.equals("Doctor")){
                        Parent root = FXMLLoader.load(getClass().getResource("DoctorDashboard.fxml"));
                        Stage stage = (Stage)(Window)SigninBtn.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        sharedname=uname;
                    }

                } else {
                    System.out.println("Please Enter Correct email Or Password");
                    Notifications.create()
                            .title("Warning")
                            .text("Invalid ID or Password")
                            .showError();


                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
   public void  autofill(ActionEvent actionEvent) {
        IdField.setText("admin");
        PasswordField.setText("admin");
        comb.setValue("Admin");
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Admin","Doctor");
        comb.setItems(list);

    }


}
