package view;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import model.database.DB_Connection;

import java.util.concurrent.Future;

public class Main extends Application {

    public static Stage primaryStage = null;
    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            DB_Connection.getConnection();
            return null;
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Khoi tao database
        task.run();
        // UI
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Main.primaryStage.setTitle("Hello World");
        Main.primaryStage.setScene(new Scene(root, 1200, 700));

        Main.primaryStage.setFullScreen(false);
        Main.primaryStage.setTitle("Quản lý học phí sinh viên nhóm x");
        Main.primaryStage.setResizable(false);
        Main.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
