// http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
// https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import control.container.MucThu_MonHoc_HocPhan;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.objects.*;
import view.Main;

public class MainUserController {

    private static Object lock = new Object();
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    @FXML
    private ResourceBundle resources;

    @FXML
    private JFXButton btnBack;

    @FXML
    private URL location;

    @FXML
    private Hyperlink address;

    @FXML
    private JFXButton btnRegis;

    @FXML
    private JFXButton btnHistoryRegis;

    @FXML
    private JFXButton btnLookTax;

    @FXML
    private JFXTextField txtSearchRegis;

    @FXML
    private TableView<?> tableRegis;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtSearchHistory;

    @FXML
    private TableView<?> tableWaitToBeSubmit;

    @FXML
    private JFXButton btnSummit;

    @FXML
    private ObservableList waitSubmitOb;

    // -----------------------------------------------------
    // -----------------------------------------------------
    // -----------------------------------------------------
    // -----------------------------------------------------

    List<MucThu_MonHoc_HocPhan> hienThiDangKy = new ArrayList<>();
    List<MucThu_MonHoc_HocPhan> hienThiXacNhan = new ArrayList<>();
    List<PhieuThu> hienThiPhieuThu = new ArrayList<>();

    Thread getThreadTableHocPhan = new Thread(() -> {
        Platform.runLater(() -> {
            hienThiDangKy = null;
//        List<MucThu> mucThus = null;
//        List<MonHoc> monHocs = null;
            try {
                hienThiDangKy = MucThu_MonHoc_HocPhan.getAll();
//            mucThus = MucThu.Search.getAll();
//            monHocs = MonHoc.Search.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

//        ObservableList observableList1 = FXCollections.observableArrayList(mucThus);
//        ObservableList observableList2 = FXCollections.observableArrayList(monHocs);

//        tableRegis.setItems(observableList1);
//        tableRegis.setItems(observableList2);
            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(hienThiDangKy);
                    tableRegis.setItems(observableList);
                    addAllTable(tableRegis);
                }
            });
        });
    });
    Thread getThreadTablePhieuThu = new Thread(() -> {
        Platform.runLater(() -> {
            TableColumn maPT = new TableColumn("Mã Phiếu Thu");
            maPT.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("ma"));

            TableColumn soTien = new TableColumn("Số Tiền");
            soTien.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("soTien"));

            TableColumn maSV = new TableColumn("Mã Sinh Viên");
            maSV.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("maSV"));

            TableColumn ngayBatDauThu = new TableColumn("Ngày Bắt Đầu Thu");
            ngayBatDauThu.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("ngayBatDauThu"));

            TableColumn ngayNop = new TableColumn("Ngày Nộp");
            ngayNop.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("ngayNop"));

            TableColumn trangThai = new TableColumn("Trạng Thái");
            trangThai.setCellValueFactory(new PropertyValueFactory<PhieuThu, String>("trangThai"));

            try {
                hienThiPhieuThu = PhieuThu.Search.whereCondition("masv = " + Main.primaryStage.getTitle());


                Platform.runLater(() -> {
                    synchronized (lock) {
                        ObservableList observableList = FXCollections.observableArrayList(
                                hienThiPhieuThu
                        );
                        tableRegis.setItems(observableList);
                        tableRegis.getColumns().addAll(maPT, maSV, soTien, ngayBatDauThu, ngayNop, trangThai);
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    });

    @FXML
    void actionDelete(MouseEvent event) {
        if (waitSubmitOb.size() > 0) {
            MucThu_MonHoc_HocPhan mt = (MucThu_MonHoc_HocPhan) tableWaitToBeSubmit.getSelectionModel().getSelectedItem();
            waitSubmitOb.remove(mt);
            hienThiXacNhan.remove(mt);
        }
    }

    @FXML
    void actionLookHistoryRegis(MouseEvent event) throws SQLException {
        // ----------------

        // because identity data must be serializable so need to be refresh object set before
        // set this project
        if (!btnBack.isDisable() || !btnRegis.isDisable()) {
            refreshAll();
        }

        initEnableAllButton();
        btnBack.setDisable(false);
        btnRegis.setDisable(true);
        btnHistoryRegis.setDisable(true);

        List<MucThu_MonHoc_HocPhan> list = null;

        // -- Add data
        list = MucThu_MonHoc_HocPhan.getHistory(Main.primaryStage.getTitle());

        // -- Nếu tồn tại dữ liệu
        if (list != null) {
            List<MucThu_MonHoc_HocPhan> finalList = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    refresh();
                    ObservableList observableList = FXCollections.observableArrayList(finalList);
                    tableRegis.setItems(observableList);
                    addAllTable(tableRegis);
                }
            });

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Lịch sử đăng ký của bạn không tồn tại!!!").showAndWait();
        }
    }

    @FXML
    void actionLookTax(MouseEvent event) throws SQLException {
        // ------------
        initDisableAllButton();
        btnBack.setDisable(false);
        btnHistoryRegis.setDisable(false);

        if (!getThreadTableHocPhan.isAlive()) {
            refreshAll();
            threadPool.execute(getThreadTablePhieuThu);
        }
    }

    @FXML
    void actionRegis(MouseEvent event) throws SQLException {
        MucThu_MonHoc_HocPhan mt = (MucThu_MonHoc_HocPhan) tableRegis.getSelectionModel().getSelectedItem();
        if (waitSubmitOb == null) {
            waitSubmitOb = FXCollections.observableArrayList(

            );
            waitSubmitOb.add(mt);
            tableWaitToBeSubmit.setItems(waitSubmitOb);
            addAllTable(tableWaitToBeSubmit);
            LichSuPhieuThu.Insert(new LichSuPhieuThu(Integer.parseInt(Main.primaryStage.getTitle()), mt.getMaDangKy()));
            hienThiXacNhan.add(mt);
        } else {
            if (waitSubmitOb.contains(mt)) {

            } else {
                waitSubmitOb.add(mt);
                LichSuPhieuThu.Insert(new LichSuPhieuThu(Integer.parseInt(Main.primaryStage.getTitle()), mt.getMaDangKy()));
                hienThiXacNhan.add(mt);
            }
        }
    }

    @FXML
    void actionSearchSubjectRegis(ActionEvent event) {
        ObservableList filterData = null;
        synchronized (lock) {
            if (!btnRegis.isDisable()) {
                filterData = FXCollections.observableArrayList(hienThiDangKy);
                filterData = filterData.filtered(o -> {
                    return ((MucThu_MonHoc_HocPhan) o).
                            getMonHoc().toLowerCase().
                            contains(txtSearchRegis.getText().toLowerCase());
                });
            } else {
                filterData = FXCollections.observableArrayList(hienThiPhieuThu);
                filterData = filterData.filtered(o -> {
                    return ((MucThu_MonHoc_HocPhan) o).
                            getMonHoc().toLowerCase().
                            contains(txtSearchRegis.getText());
                });
            }

            ObservableList finalFilterData = filterData;
            Platform.runLater(() -> {
                tableRegis.setItems(finalFilterData);
            });
        }
    }

    @FXML
    void actionSearchSubjectRegisted(ActionEvent event) throws SQLException {
        synchronized (lock) {
            if (!btnBack.isDisable()) {
                refreshAll();
            }
            initEnableAllButton();
            btnBack.setDisable(false);
            btnRegis.setDisable(true);

            List<MucThu_MonHoc_HocPhan> list = null;

            // -- Add data
            list = MucThu_MonHoc_HocPhan.getHistory(Main.primaryStage.getTitle());

            // -- Nếu tồn tại dữ liệu
            if (list != null) {
                refresh();


                List<MucThu_MonHoc_HocPhan> finalList = list;
                Platform.runLater(() -> {
                    synchronized (lock) {
                        ObservableList observableList = FXCollections.observableArrayList(finalList);
                        tableRegis.setItems(observableList);
                        addAllTable(tableRegis);
                    }
                });
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Lịch sử đăng ký của bạn không tồn tại!!!").showAndWait();
            }
        }
    }

    @FXML
    void actionSubmit(MouseEvent event) throws SQLException {
        if (waitSubmitOb.size() != 0) {
//            DangKy dk = new DangKy()
            long soTien = 0;
            for (int i = 0; i < waitSubmitOb.size(); i++) {
                soTien += ((MucThu_MonHoc_HocPhan) waitSubmitOb.get(i)).getSoTien();
//                LichSuPhieuThu.Insert(lichSuPhieuThu);
            }
            System.out.println(soTien);
            PhieuThu pt = new PhieuThu(
                    Integer.parseInt(Main.primaryStage.getTitle()), soTien,
                    new Date(System.currentTimeMillis()).toString(), null, false
            );
            System.out.println(pt);

            PhieuThu.Insert(pt);
        }
    }

    // 15199987
    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'SV.fxml'.";
        assert btnRegis != null : "fx:id=\"btnRegis\" was not injected: check your FXML file 'SV.fxml'.";
        assert btnHistoryRegis != null : "fx:id=\"btnHistoryRegis\" was not injected: check your FXML file 'SV.fxml'.";
        assert btnLookTax != null : "fx:id=\"btnLookTax\" was not injected: check your FXML file 'SV.fxml'.";
        assert txtSearchRegis != null : "fx:id=\"txtSearchRegis\" was not injected: check your FXML file 'SV.fxml'.";
        assert tableRegis != null : "fx:id=\"tableRegis\" was not injected: check your FXML file 'SV.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'SV.fxml'.";
        assert txtSearchHistory != null : "fx:id=\"txtSearchHistory\" was not injected: check your FXML file 'SV.fxml'.";
        assert tableWaitToBeSubmit != null : "fx:id=\"tableWaitToBeSubmit\" was not injected: check your FXML file 'SV.fxml'.";
        assert btnSummit != null : "fx:id=\"btnSummit\" was not injected: check your FXML file 'SV.fxml'.";

        threadPool.execute(getThreadTableHocPhan);

//        getThreadTableMonHoc.start();

    }

    /**
     * Add column for tableView
     *
     * @param tableView
     */
    void addAllTable(TableView tableView) {
//        private SimpleIntegerProperty maHocPhan, soTinChi;
//        private SimpleDoubleProperty mucThu, soTien;
//        private SimpleStringProperty monHoc, giaoVienGiangDay, thoiGian, tenGD, maDangKy;
        Thread t_AddAllTable = new Thread(() -> {

            TableColumn ma = new TableColumn("Mã học phần");
            ma.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("maHocPhan"));

            TableColumn maDangKy = new TableColumn("Mã học đăng ký");
            maDangKy.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("maDangKy"));

            TableColumn giangDuong = new TableColumn("Tên giảng đường");
            giangDuong.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("tenGD"));

            TableColumn soTienMotTinChi = new TableColumn("Số tiền một tín chỉ");
            soTienMotTinChi.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("mucThu"));
//
            TableColumn tongSoTien = new TableColumn("Tổng số tiền");
            tongSoTien.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("soTien"));
//
            TableColumn monHoc = new TableColumn("Môn học");
            monHoc.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("monHoc"));
//
            TableColumn soTinChi = new TableColumn("Số tín chỉ");
            soTinChi.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("soTinChi"));
//
            TableColumn giaoVienGiangDay = new TableColumn("Giáo viên");
            giaoVienGiangDay.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("giaoVienGiangDay"));

            TableColumn thoiGian = new TableColumn("Thời gian bắt đầu");
            thoiGian.setCellValueFactory(new PropertyValueFactory<MucThu_MonHoc_HocPhan, String>("thoiGian"));

            Platform.runLater(() -> {
                synchronized (lock) {
                    tableView.getColumns().addAll(maDangKy, ma, monHoc, giaoVienGiangDay, soTinChi, soTienMotTinChi, tongSoTien,
                            giangDuong, thoiGian);
                }
            });

        });

        threadPool.execute(t_AddAllTable);
    }

    private void refreshAll() {
        tableRegis.getColumns().clear();
        refresh();
    }


    @FXML
    void bringMeBack(MouseEvent event) {
        // cho về trạng thái cũ
        initEnableAllButton();
        btnBack.setDisable(true);


        ObservableList observableList = null;
        if (hienThiDangKy != null) {
            observableList = FXCollections.observableArrayList(
                    hienThiDangKy
            );
        }

        refreshAll();
        tableRegis.setItems(observableList);
        addAllTable(tableRegis);
    }

    public void refresh() {
        tableRegis.getItems().clear();
        tableRegis.refresh();
    }

    public void initEnableAllButton() {
        if (btnBack.isDisable()) {
            btnBack.setDisable(false);
        }
        if (btnHistoryRegis.isDisable()) {
            btnHistoryRegis.setDisable(false);
        }
        if (btnLookTax.isDisable()) {
            btnLookTax.setDisable(false);
        }
        if (btnRegis.isDisable()) {
            btnRegis.setDisable(false);
        }
    }

    /**
     * Nếu muốn xem tất cả cái khác ngoài xem phiếu thu thì gọi cái này
     */
    public void initDisableAllButton() {
        if (!btnBack.isDisable()) {
            btnBack.setDisable(true);
        }
        if (!btnHistoryRegis.isDisable()) {
            btnHistoryRegis.setDisable(true);
        }
        if (!btnLookTax.isDisable()) {
            btnLookTax.setDisable(true);
        }
        if (!btnRegis.isDisable()) {
            btnRegis.setDisable(true);
        }
    }


}
