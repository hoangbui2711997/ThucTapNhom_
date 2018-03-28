package control.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.objects.BoMon;
import view.Main;

import java.io.IOException;
import java.sql.SQLException;

public class InsertBM {
    @FXML
    private JFXTextField txtNewName;

    @FXML
    private JFXButton btnXacNhan;

    @FXML
    private JFXButton btnHuy;

    @FXML
    private Hyperlink address;

    public void onXacNhanClick(ActionEvent actionEvent) throws InterruptedException {
        String newBoMonName = txtNewName.getText();
        if(newBoMonName.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Chưa nhập tên bộ môn!").showAndWait();
        }
        else{


            BoMon boMon= new BoMon(txtNewName.getText());
            try {
                boMon = BoMon.Insert(boMon);
                MainController.allData[1].add(boMon);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) btnHuy.getScene().getWindow();
            stage.close();
        }
    }

    public void onHuyClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }
    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'InsertBM.fxml'.";
        assert txtNewName != null : "fx:id=\"txtNewName\" was not injected: check your FXML file 'InsertBM.fxml'.";
        assert btnXacNhan != null : "fx:id=\"btnXacNhan\" was not injected: check your FXML file 'InsertBM.fxml'.";
        assert btnHuy != null : "fx:id=\"btnHuy\" was not injected: check your FXML file 'InsertBM.fxml'.";
    }
}