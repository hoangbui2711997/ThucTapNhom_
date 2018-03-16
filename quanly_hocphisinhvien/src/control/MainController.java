package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.database.DB_Connection;
import model.objects.BoMon;
import model.objects.DangKy;

public class MainController<T>{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink address;

    @FXML
    private JFXComboBox<?> selectTable;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton repair;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXTextField search;

    @FXML
    private TableView<?> table;

    Thread getThreadTableDangKy = new Thread(
            () -> {
                TableColumn ma = new TableColumn("Mã đăng ký");
                ma.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("maDangKy")
                );

                TableColumn gd = new TableColumn("Mã giảng đường");
                gd.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("maGiangDuong")
                );

                TableColumn hp = new TableColumn("Mã học phần");
                hp.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("maHocPhan")
                );

                TableColumn sv = new TableColumn("Mã sinh viên");
                sv.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("maSinhVien")
                );

                TableColumn dk = new TableColumn("Thời gian đăng ký");
                dk.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("thoiGianDangKy")
                );
                //

                List<DangKy> list = null;
                try {
                    list = DangKy.Search.getAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ObservableList observableList = FXCollections.observableArrayList(
                        list
                );
                Platform.runLater(() -> {
                            table.setItems(observableList);
                            table.getColumns().addAll(ma, gd, hp, sv, dk);
                            System.out.println("Run later");
                        }
                );
                System.out.println("I'm running");
            }
    );

    Thread getThreadTableBoMon = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã bộ môn");
        ma.setCellValueFactory(
                new PropertyValueFactory<BoMon, String>("ma")
        );

        TableColumn ten = new TableColumn("Tên bộ môn");
        ten.setCellValueFactory(
                new PropertyValueFactory<BoMon, String>("ten")
        );

        try {
            ObservableList observableList = FXCollections.observableArrayList(
                    BoMon.Search.getAll()
            );

            table.setItems(observableList);
            Platform.runLater(
                    () -> {
                        table.getColumns().addAll(ma, ten);
                    }
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });

    @FXML
    void TestHidden(MouseEvent event) throws Exception {
        System.out.println(selectTable.getTypeSelector());

        // add từ item vào combobox
//        selectTable.getItems().addAll();
        // lấy dữ liệu từ selected trong combobox
//        System.out.println(selectTable.getValue() == "Đăng ký");

//        task.run();

//        threadGetTableDangKy.run();
//        getThreadTableDangKy.run();
        
        table.getItems().removeAll();
//        System.out.println("Who run before");
    }

    /**
     * File chay khoi tao
     */
    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'UI.fxml'.";
        assert selectTable != null : "fx:id=\"selectTable\" was not injected: check your FXML file 'UI.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'UI.fxml'.";
        assert repair != null : "fx:id=\"repair\" was not injected: check your FXML file 'UI.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'UI.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'UI.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'UI.fxml'.";

        ArrayList arrayList = new ArrayList();
        arrayList.add("Bộ môn");
        arrayList.add("Đăng ký");
        arrayList.add("Đối tượng");
        arrayList.add("Giảng đương");
        arrayList.add("Học phần");
        arrayList.add("Khoa");
        arrayList.add("Môn học");
        arrayList.add("Mức thu");
        arrayList.add("Ngành");
        arrayList.add("Phiếu thu");
        arrayList.add("Sinh viên");

        // Khoi tao database
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                DB_Connection.getConnection();
                return null;
            }
        };
        task.run();

        //
        selectTable.getItems().addAll(arrayList);
        getThreadTableBoMon.run();
    }
}
