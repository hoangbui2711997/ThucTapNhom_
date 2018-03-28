package view;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.database.DB_Connection;

import java.util.concurrent.Future;

public class Main extends Application {

    public static Stage primaryStage = null;

    Thread runDatabase = new Thread(() -> {
       DB_Connection.getConnection();
    });

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Khoi tao database
        runDatabase.start();
        // UI
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Main.primaryStage.setTitle("Hello World");
        Main.primaryStage.setScene(new Scene(root, 1200, 700));

        Main.primaryStage.setFullScreen(false);
        Main.primaryStage.setTitle("Quản lý học phí sinh viên nhóm x");
        Main.primaryStage.setResizable(false);
        Main.primaryStage.show();
        Main.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println();
                System.exit(1);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
