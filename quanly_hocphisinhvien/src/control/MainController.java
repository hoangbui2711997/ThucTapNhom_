package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.database.DB_Connection;
import model.objects.*;

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
    });

    Thread getThreadTableBoMon = new Thread(() -> {
        // Tao column cho table nhe
        TableColumn ma = new TableColumn("Mã bộ môn");
        // sinh ra phan tu? tu. dong
        ma.setCellValueFactory(
                // tu cai nay
                // phai co phuong thuc get tu Object
                // Cai nay la ma bo mon
                // ma la ten property
                new PropertyValueFactory<BoMon, String>("ma")
        );
// tuong tu
        TableColumn ten = new TableColumn("Tên bộ môn");
        ten.setCellValueFactory(
                new PropertyValueFactory<BoMon, String>("ten")
        );

        try {
            // cái này là đổ dữ liệu vào table
            ObservableList observableList = FXCollections.observableArrayList(
                    // getAll trả về list của của bộ môn Ctrl + Q xem định nghĩa
                    BoMon.Search.getAll()
            );

            // Đây là set cell, hay là phần tử trong bảng
            table.setItems(observableList);
            // Run later biết cái này chưa nói đi
            // Khi mà ông chạy 1 thread -> sang cái function này nhé
            // Nhưng mà trong lúc đấy nó lại gọi form của thread gọi tới thread này
            // => bị lõi. Cho nên phải chờ funciton này thực hiện xong nó mới quay trở lại
            // Thread ban đầu gọi tới thread này
            // Plateform.runLater thực hiện điều này (nghĩa là nó thực hiện sau khi function kết thúc)
            ///
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

        TableColumn giaoVienGiangDay = new TableColumn("Giáo viên");
        giaoVienGiangDay.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("giaoVienGiangDay"));

        TableColumn thoiGian = new TableColumn("Thời gian bắt đầu");
        thoiGian.setCellValueFactory(new PropertyValueFactory<HocPhan, String>("thoiGian"));

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
        maDT.setCellValueFactory(new PropertyValueFactory<SinhVien, String>("maDT"));

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
                table.getColumns().addAll(maSV, maDT, maBoMon, tenSV, gioiTinh, ngaySinh,  diaChi);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTablePhieuThu = new Thread(() -> {
        TableColumn maPT = new TableColumn("Mã Phiếu Thu");
        maPT.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("maSV"));

        TableColumn soTien = new TableColumn("Số Tiền");
        soTien.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("maDT"));

        TableColumn maSV = new TableColumn("Mã Sinh Viên");
        maSV.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("maBoMon"));

        TableColumn ngayBatDauThu = new TableColumn("Ngày Bắt Đầu Thu");
        ngayBatDauThu.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("tenSV"));

        TableColumn ngayNop = new TableColumn("Ngày Nộp");
        ngayNop.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("gioiTinh"));

        TableColumn trangThai = new TableColumn("Trạng Thái");
        trangThai.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("ngaySinh"));

        try{
            ObservableList observableList = FXCollections.observableArrayList(PhieuThu.Search.getAll());
            table.setItems(observableList);

            Platform.runLater(() -> {
                table.getColumns().addAll(maPT, maSV, soTien, ngayBatDauThu, ngayNop, trangThai);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    @FXML
    void TestHidden(MouseEvent event) throws Exception {
//        System.out.println(selectTable.getTypeSelector());

        // add từ item vào combobox
//        selectTable.getItems().addAll();
        // lấy dữ liệu từ selected trong combobox
//        System.out.println(selectTable.getValue() == "Đăng ký");

//        task.run();

//        getThreadTableDangKy.run();
//        getThreadTableHocPhan.run();
//        table.getItems().removeAll();
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
        arrayList.add("Giảng đường");
        arrayList.add("Học phần");
        arrayList.add("Khoa");
        arrayList.add("Môn học");
        arrayList.add("Mức thu");
        arrayList.add("Ngành");
        arrayList.add("Phiếu thu");
        arrayList.add("Sinh viên");

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
                case "Phiếu thu":
                    refreshTableView();
                    getThreadTablePhieuThu.run();
                    break;
            }
        });
    }

    private void refreshTableView() {
        table.getItems().clear();
        table.getColumns().clear();
        table.refresh();
    }

    public void onAddButtonClicked(){
        switch(selectTable.getValue().toString()){
            case "Bộ môn":
//                BoMon.Insert()
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

    }

    public void onDeleteButtonClicked(){
        switch(selectTable.getValue().toString()){
            case "Bộ môn":
                BoMon boMon = (BoMon) table.getSelectionModel().getSelectedItem();
                BoMon.Delete.whereId("" + boMon.getMa());
                refreshTableView();
                getThreadTableBoMon.run();
                break;
            case "Đăng ký":
                DangKy dangKy = (DangKy) table.getSelectionModel().getSelectedItem();
                DangKy.Delete.whereId(dangKy.getMaDangKy());
                refreshTableView();
                getThreadTableDangKy.run();
                break;
            case "Đối tượng":
                DoiTuong doiTuong = (DoiTuong) table.getSelectionModel().getSelectedItem();
                DoiTuong.Delete.whereId("" + doiTuong.getMa());
                refreshTableView();
                getThreadTableDoiTuong.run();
                break;
            case "Giảng đường":
                GiangDuong giangDuong = (GiangDuong) table.getSelectionModel().getSelectedItem();
                GiangDuong.Delete.whereId("" + giangDuong.getMa());
                refreshTableView();
                getThreadTableGiangDuong.run();
                break;
            case "Học phần":
                HocPhan hocPhan = (HocPhan) table.getSelectionModel().getSelectedItem();
                HocPhan.Delete.whereId("" + hocPhan.getMa());
                refreshTableView();
                getThreadTableHocPhan.run();
                break;
            case "Khoa":
                Khoa khoa = (Khoa) table.getSelectionModel().getSelectedItem();
                Khoa.Delete.whereId("" + khoa.getMa());
                refreshTableView();
                getThreadTableKhoa.run();
                break;
            case "Môn học":
                MonHoc monHoc = (MonHoc) table.getSelectionModel().getSelectedItem();
                MonHoc.Delete.whereId("" + monHoc.getMa());
                refreshTableView();
                getThreadTableMonHoc.run();
                break;
            case "Mức thu":
                MucThu mucThu = (MucThu) table.getSelectionModel().getSelectedItem();
                MucThu.Delete.whereId("" + mucThu.getMaMucThu());
                refreshTableView();
                getThreadTableMucThu.run();
                break;
            case "Ngành":
                Nganh nganh = (Nganh) table.getSelectionModel().getSelectedItem();
                MucThu.Delete.whereId("" + nganh.getMa());
                refreshTableView();
                getThreadTableNganh.run();
                break;
            case "Sinh viên":
                SinhVien sinhVien = (SinhVien) table.getSelectionModel().getSelectedItem();
                SinhVien.Delete.whereId("" + sinhVien.getMaSV());
                refreshTableView();
                getThreadTableSinhVien.run();
                break;
        }
    }
}