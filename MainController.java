package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.database.DB_Connection;
import model.objects.*;
import view.Main;

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

    Thread getThreadTableDangKy = new Thread(() -> {
                TableColumn ma = new TableColumn("Mã đăng ký");
                ma.setCellValueFactory(
                        new PropertyValueFactory<DangKy, String>("maDangKy")
                );

                TableColumn gd = new TableColumn("Mã giảng đường");
                gd.setCellValueFactory(new PropertyValueFactory<DangKy, String>("maGiangDuong"));

                TableColumn hp = new TableColumn("Mã học phần");
                hp.setCellValueFactory(new PropertyValueFactory<DangKy, String>("maHocPhan"));

                TableColumn sv = new TableColumn("Mã sinh viên");
                sv.setCellValueFactory(new PropertyValueFactory<DangKy, String>("maSinhVien"));

                TableColumn dk = new TableColumn("Thời gian đăng ký");
                dk.setCellValueFactory(new PropertyValueFactory<DangKy, String>("thoiGianDangKy"));

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
            });

    Thread getThreadTableBoMon = new Thread(() -> {
        // Tao column cho table nhe
        TableColumn ma = new TableColumn("Mã bộ môn");
        // sinh ra phan tu? tu. dong
        ma.setCellValueFactory(new PropertyValueFactory<BoMon, String>("ma"));

        TableColumn ten = new TableColumn("Tên bộ môn");
        ten.setCellValueFactory(new PropertyValueFactory<BoMon, String>("ten"));
        ten.setCellFactory(TextFieldTableCell.<BoMon> forTableColumn());

        ten.setOnEditCommit(new EventHandler<CellEditEvent<BoMon, String>>() {
            @Override
            public void handle(CellEditEvent<BoMon, String> event) {
                TablePosition<BoMon, String> pos = event.getTablePosition();
                String newName = event.getNewValue();

                int row = pos.getRow();
                BoMon boMon = event.getTableView().getItems().get(row);
                boMon.setTen(newName);

                try {
                    BoMon.Update.where(boMon.getMa(), boMon);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            // cái này là đổ dữ liệu vào table
            ObservableList observableList = FXCollections.observableArrayList(BoMon.Search.getAll());

            // Đây là set cell, hay là phần tử trong bảng
            table.setItems(observableList);
            Platform.runLater(
                    () -> {
                        // Cái này là tên cột
                        table.getColumns().addAll(ma, ten);
                    }
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });

    Thread getThreadTableDoiTuong = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã đối tượng");
        ma.setCellValueFactory(new PropertyValueFactory<DoiTuong, String>("ma"));

        TableColumn ten = new TableColumn("Tên đối tượng");
        ten.setCellValueFactory(new PropertyValueFactory<DoiTuong, String>("ten"));
        ten.setCellFactory(TextFieldTableCell.<DoiTuong>forTableColumn());
        ten.setOnEditCommit(new EventHandler<CellEditEvent<DoiTuong, String>>() {
            @Override
            public void handle(CellEditEvent<DoiTuong, String> event) {
                TablePosition<DoiTuong, String> pos = event.getTablePosition();
                String newName = event.getNewValue();
                int row = pos.getRow();

                DoiTuong doiTuong = event.getTableView().getItems().get(row);
                doiTuong.setTen(newName);
                try {
                    DoiTuong.Update.where(doiTuong.getMa(), doiTuong);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        try{
            ObservableList observableList = FXCollections.observableArrayList(DoiTuong.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, ten);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableGiangDuong = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã giảng đường");
        ma.setCellValueFactory(new PropertyValueFactory<GiangDuong, String>("ma"));

        TableColumn ten = new TableColumn("Tên giảng đường");
        ten.setCellValueFactory(new PropertyValueFactory<GiangDuong, String>("ten"));
        ten.setCellFactory(TextFieldTableCell.<GiangDuong>forTableColumn());
        ten.setOnEditCommit(new EventHandler<CellEditEvent<GiangDuong, String>>() {
            @Override
            public void handle(CellEditEvent<GiangDuong, String> event) {
                TablePosition<GiangDuong, String> pos = event.getTablePosition();
                String newValue = event.getNewValue();
                int row = pos.getRow();
                GiangDuong giangDuong = event.getTableView().getItems().get(row);
                giangDuong.setTen(newValue);

                try {
                    GiangDuong.Update.where("magd = " + giangDuong.getMa(), giangDuong);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        try{
            ObservableList observableList = FXCollections.observableArrayList(GiangDuong.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, ten);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });

    Thread getThreadTableHocPhan = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã học phần");
        ma.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("ma"));

        TableColumn maMucThu = new TableColumn("Mã mức thu");
        maMucThu.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("maMucThu"));

        TableColumn maMonHoc = new TableColumn("Mã môn học");
        maMonHoc.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("maMonHoc"));

        TableColumn soTinChi = new TableColumn("Số tín chỉ");
        soTinChi.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("soTinChi"));
        soTinChi.setCellFactory(TextFieldTableCell.<HocPhan, Integer>forTableColumn(new IntegerStringConverter()));
        soTinChi.setOnEditCommit(new EventHandler<CellEditEvent<HocPhan, Integer>>() {
            @Override
            public void handle(CellEditEvent<HocPhan, Integer> event) {
                TablePosition<HocPhan, Integer> pos = event.getTablePosition();
                int newValue = event.getNewValue();
                int row = pos.getRow();
                HocPhan hocPhan = event.getTableView().getItems().get(row);
                hocPhan.setSoTinChi(newValue);
                try {
                    HocPhan.Update.where("mahp = " + hocPhan.getMa(), hocPhan);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        TableColumn giaoVienGiangDay = new TableColumn("Giáo viên");
        giaoVienGiangDay.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("giaoVienGiangDay"));
        giaoVienGiangDay.setCellFactory(TextFieldTableCell.<HocPhan>forTableColumn());
        giaoVienGiangDay.setOnEditCommit(new EventHandler<CellEditEvent<HocPhan, String>>() {
            @Override
            public void handle(CellEditEvent<HocPhan, String> event) {
                TablePosition<HocPhan, String> pos = event.getTablePosition();
                String newValue = event.getNewValue();
                int row = pos.getRow();
                HocPhan hocPhan = event.getTableView().getItems().get(row);
                hocPhan.setGiaoVienGiangDay(newValue);
                try {
                    HocPhan.Update.where("mahp = " + hocPhan.getMa(), hocPhan);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        TableColumn thoiGian = new TableColumn("Thời gian bắt đầu");
        thoiGian.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("thoiGian"));
        thoiGian.setCellFactory(TextFieldTableCell.<HocPhan>forTableColumn());
        thoiGian.setOnEditCommit(new EventHandler<CellEditEvent<HocPhan, String>>() {
            @Override
            public void handle(CellEditEvent<HocPhan, String> event) {
//                TablePosition<HocPhan, String> pos = event.getTablePosition();
//                String newValue = event.getNewValue();
//                int row = pos.getRow();
//                HocPhan hocPhan = event.getTableView().getItems().get(row);
//                DateFormat format = new SimpleDateFormat("yyyy-mm-đd");
//                try {
//                    java.util.Date newDate = format.parse(newValue);
//                    hocPhan.setThoiGian(new Date(newDate.getTime()));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    HocPhan.Update.where("mahp = " + hocPhan.getMa(), hocPhan);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
            }
        });

        try{
            ObservableList observableList = FXCollections.observableArrayList(HocPhan.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, maMonHoc, soTinChi, maMucThu , giaoVienGiangDay, thoiGian);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableKhoa = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã khoa");
        ma.setCellValueFactory(new PropertyValueFactory<Khoa, String>("ma"));

        TableColumn ten = new TableColumn("Tên khoa");
        ten.setCellValueFactory(new PropertyValueFactory<Khoa, String>("ten"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(Khoa.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, ten);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableMonHoc = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã môn học");
        ma.setCellValueFactory(new PropertyValueFactory<MonHoc, String>("ma"));

        TableColumn ten = new TableColumn("Tên môn học");
        ten.setCellValueFactory(new PropertyValueFactory<MonHoc, String>("ten"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(MonHoc.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, ten);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableMucThu = new Thread(() -> {
        TableColumn maMucThu = new TableColumn("Mã mức thu");
        maMucThu.setCellValueFactory(new PropertyValueFactory<MucThu, String>("maMucThu"));

        TableColumn moTa = new TableColumn("Mô tả");
        moTa.setCellValueFactory(new PropertyValueFactory<MucThu, String>("moTa"));

        TableColumn soTien = new TableColumn("Số tiền");
        soTien.setCellValueFactory(new PropertyValueFactory<MucThu, String>("soTien"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(MucThu.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(maMucThu, moTa, soTien);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableNganh = new Thread(() -> {
        TableColumn ma = new TableColumn("Mã ngành");
        ma.setCellValueFactory(new PropertyValueFactory<Nganh, String>("ma"));

        TableColumn ten = new TableColumn("Tên ngành");
        ten.setCellValueFactory(new PropertyValueFactory<Nganh, String>("ten"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(Nganh.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(ma, ten);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTableSinhVien = new Thread(() -> {
        TableColumn maSV = new TableColumn("Mã sinh viên");
        maSV.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maSV"));

        TableColumn maDT = new TableColumn("Mã dt");
        maDT.setCellValueFactory(new PropertyValueFactory<GiangDuong, String>("maDT"));

        TableColumn maBoMon = new TableColumn("Mã bộ môn");
        maBoMon.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maBoMon"));

        TableColumn tenSV = new TableColumn("Tên sinh viên");
        tenSV.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("tenSV"));

        TableColumn gioiTinh = new TableColumn("Giới tính");
        gioiTinh.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("gioiTinh"));

        TableColumn ngaySinh = new TableColumn("Ngày sinh");
        ngaySinh.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("ngaySinh"));

        TableColumn diaChi = new TableColumn("Địa chỉ");
        diaChi.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("diaChi"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(SinhVien.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(maSV, maDT, maBoMon, tenSV, diaChi, ngaySinh,  gioiTinh);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

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
        arrayList.add("Giảng đường");
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
        selectTable.setOnAction((e) -> {
            switch(selectTable.getValue().toString()){
                case "Bộ môn":
                    refreshTableView();
                    getThreadTableBoMon.run();
                    break;
                case "Đăng ký":
                    refreshTableView();
                    getThreadTableDangKy.run();
                    break;
                case "Đối tượng":
                    refreshTableView();
                    getThreadTableDoiTuong.run();
                    break;
                case "Giảng đường":
                    refreshTableView();
                    getThreadTableGiangDuong.run();
                    break;
                case "Học phần":
                    refreshTableView();
                    getThreadTableHocPhan.run();
                    break;
                case "Khoa":
                    refreshTableView();
                    getThreadTableKhoa.run();
                    break;
                case "Môn học":
                    refreshTableView();
                    getThreadTableMonHoc.run();
                    break;
                case "Mức thu":
                    refreshTableView();
                    getThreadTableMucThu.run();
                    break;
                case "Ngành":
                    refreshTableView();
                    getThreadTableNganh.run();
                    break;
                case "Sinh viên":
                    refreshTableView();
                    getThreadTableSinhVien.run();
                    break;
            }
        });

        table.setEditable(true);
    }

    private void refreshTableView() {
        table.getItems().clear();
        table.getColumns().clear();
        table.refresh();
    }

    public void onAddButtonClicked(){
        switch(selectTable.getValue().toString()){
            case "Bộ môn":
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/InsertBoMon.fxml"));
                    Stage insertStage = new Stage();
                    insertStage.setScene(new Scene(root));
                    insertStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Đăng ký":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableDangKy.run();
                break;
            case "Đối tượng":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableDoiTuong.run();
                break;
            case "Giảng đường":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableGiangDuong.run();
                break;
            case "Học phần":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableHocPhan.run();
                break;
            case "Khoa":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableKhoa.run();
                break;
            case "Môn học":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableMonHoc.run();
                break;
            case "Mức thu":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableMucThu.run();
                break;
            case "Ngành":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableNganh.run();
                break;
            case "Sinh viên":
                table.getItems().clear();
                table.getColumns().clear();
                table.refresh();
                getThreadTableSinhVien.run();
                break;
        }
    }

    public void onUpdateButtonClicked(){
        switch(selectTable.getValue().toString()){
            case "Bộ môn":
                refreshTableView();
                getThreadTableBoMon.run();
                break;
            case "Đăng ký":
                DangKy dangKy = (DangKy) table.getSelectionModel().getSelectedItem();
                DangKy.Delete.where(dangKy.getMaDangKy());
                refreshTableView();
                getThreadTableDangKy.run();
                break;
            case "Đối tượng":
                DoiTuong doiTuong = (DoiTuong) table.getSelectionModel().getSelectedItem();
                System.out.println((doiTuong.getMa())+ "");
                SinhVien.Update.set_madt_null(doiTuong.getMa());
                DoiTuong.Delete.where(doiTuong.getMa());
                refreshTableView();
                getThreadTableDoiTuong.run();
                break;
            case "Giảng đường":
                GiangDuong giangDuong = (GiangDuong) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_magd_null(giangDuong.getMa());
                GiangDuong.Delete.where(giangDuong.getMa());
                refreshTableView();
                getThreadTableGiangDuong.run();
                break;
            case "Học phần":
                HocPhan hocPhan = (HocPhan) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_mahp_null(hocPhan.getMa());
                HocPhan.Delete.where(hocPhan.getMa());
                refreshTableView();
                getThreadTableHocPhan.run();
                break;
            case "Khoa":
                Khoa khoa = (Khoa) table.getSelectionModel().getSelectedItem();
                BoMon.Update.set_makhoa_null(khoa.getMa());
                Nganh.Update.set_makhoa_null(khoa.getMa());
                Khoa.Delete.where(khoa.getMa());
                refreshTableView();
                getThreadTableKhoa.run();
                break;
            case "Môn học":
                MonHoc monHoc = (MonHoc) table.getSelectionModel().getSelectedItem();
                HocPhan.Update.set_mamonhoc_null(monHoc.getMa());
                MonHoc.Delete.where(monHoc.getMa());
                refreshTableView();
                getThreadTableMonHoc.run();
                break;
            case "Mức thu":
                MucThu mucThu = (MucThu) table.getSelectionModel().getSelectedItem();
                MonHoc.Delete.where(mucThu.getMaMucThu());
                refreshTableView();
                getThreadTableMucThu.run();
                break;
            case "Ngành":
                Nganh nganh = (Nganh) table.getSelectionModel().getSelectedItem();
                Nganh.Delete.where(nganh.getMa());
                refreshTableView();
                getThreadTableNganh.run();
                break;
            case "Sinh viên":
                SinhVien sinhVien = (SinhVien) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_masv_null(sinhVien.getMaSV());
                SinhVien.Delete.where(sinhVien.getMaSV());
                refreshTableView();
                getThreadTableSinhVien.run();
                break;
        }
    }

    public void onDeleteButtonClicked(){
        switch(selectTable.getValue().toString()){
            case "Bộ môn":
                BoMon boMon = (BoMon) table.getSelectionModel().getSelectedItem();
                BoMon.Delete.where(boMon.getMa());
                refreshTableView();
                getThreadTableBoMon.run();
                break;
            case "Đăng ký":
                DangKy dangKy = (DangKy) table.getSelectionModel().getSelectedItem();
                DangKy.Delete.where(dangKy.getMaDangKy());
                refreshTableView();
                getThreadTableDangKy.run();
                break;
            case "Đối tượng":
                DoiTuong doiTuong = (DoiTuong) table.getSelectionModel().getSelectedItem();
                System.out.println((doiTuong.getMa())+ "");
                SinhVien.Update.set_madt_null(doiTuong.getMa());
                DoiTuong.Delete.where(doiTuong.getMa());
                refreshTableView();
                getThreadTableDoiTuong.run();
                break;
            case "Giảng đường":
                GiangDuong giangDuong = (GiangDuong) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_magd_null(giangDuong.getMa());
                GiangDuong.Delete.where(giangDuong.getMa());
                refreshTableView();
                getThreadTableGiangDuong.run();
                break;
            case "Học phần":
                HocPhan hocPhan = (HocPhan) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_mahp_null(hocPhan.getMa());
                HocPhan.Delete.where(hocPhan.getMa());
                refreshTableView();
                getThreadTableHocPhan.run();
                break;
            case "Khoa":
                Khoa khoa = (Khoa) table.getSelectionModel().getSelectedItem();
                BoMon.Update.set_makhoa_null(khoa.getMa());
                Nganh.Update.set_makhoa_null(khoa.getMa());
                Khoa.Delete.where(khoa.getMa());
                refreshTableView();
                getThreadTableKhoa.run();
                break;
            case "Môn học":
                MonHoc monHoc = (MonHoc) table.getSelectionModel().getSelectedItem();
                HocPhan.Update.set_mamonhoc_null(monHoc.getMa());
                MonHoc.Delete.where(monHoc.getMa());
                refreshTableView();
                getThreadTableMonHoc.run();
                break;
            case "Mức thu":
                MucThu mucThu = (MucThu) table.getSelectionModel().getSelectedItem();
                MonHoc.Delete.where(mucThu.getMaMucThu());
                refreshTableView();
                getThreadTableMucThu.run();
                break;
            case "Ngành":
                Nganh nganh = (Nganh) table.getSelectionModel().getSelectedItem();
                Nganh.Delete.where(nganh.getMa());
                refreshTableView();
                getThreadTableNganh.run();
                break;
            case "Sinh viên":
                SinhVien sinhVien = (SinhVien) table.getSelectionModel().getSelectedItem();
                DangKy.Update.set_masv_null(sinhVien.getMaSV());
                SinhVien.Delete.where(sinhVien.getMaSV());
                refreshTableView();
                getThreadTableSinhVien.run();
                break;
        }
    }
}
