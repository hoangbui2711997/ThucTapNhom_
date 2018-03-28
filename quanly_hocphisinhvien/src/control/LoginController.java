package control;

import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import model.objects.SinhVien;
import model.objects.User;
import view.Main;

public class LoginController {

    @FXML
    private JFXSpinner spinStudent;

    @FXML
    private JFXSpinner spinManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink address;

    @FXML
    private JFXTextField txtManager;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXTextField txtStudentCode;

    @FXML
    private JFXButton btnSignInStudent;

    @FXML
    void checkSignInManager(ActionEvent event) throws SQLException, IOException {
        new Thread(() -> {
            Platform.runLater(() -> {
                String user = txtManager.getText();
                String passWord = txtPassword.getText();
                btnSignIn.setVisible(false);
                spinManager.setVisible(true);
                try {
                    if (User.checkSignin(user, passWord)) {
                        Parent root = FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
                        Main.primaryStage.setScene(new Scene(root, 1200, 700));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Sai mật khẩu hoặc tài khoản đăng nhập!!!").showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnSignIn.setVisible(true);
                spinManager.setVisible(false);
            });
        }).start();
    }

    @FXML
    void checkSignInStudent(ActionEvent event) throws IOException, SQLException {
        new Thread(() -> {
            Platform.runLater(() -> {
            btnSignInStudent.setVisible(false);
            spinStudent.setVisible(true);
            try {
                if(SinhVien.Search.where("masv = " + txtStudentCode.getText()) != null) {
                    Parent root = FXMLLoader.load(getClass().getResource("../view/SV.fxml"));
                    Main.primaryStage.setTitle(txtStudentCode.getText());
                    Main.primaryStage.setScene(new Scene(root, 1200, 700));
                } else {
                    new Alert(Alert.AlertType.ERROR, "Mã sinh viên không đúng!!!").showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            spinStudent.setVisible(false);
            btnSignInStudent.setVisible(true);
        });
        }).start();
    }

    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtManager != null : "fx:id=\"txtManager\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnSignIn != null : "fx:id=\"btnSignIn\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtStudentCode != null : "fx:id=\"txtStudentCode\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnSignInStudent != null : "fx:id=\"btnSignInStudent\" was not injected: check your FXML file 'Login.fxml'.";
    }
}
