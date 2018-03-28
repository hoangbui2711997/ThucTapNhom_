package control;

import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import control.add.InsertBM;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import model.objects.*;

import javax.script.Bindings;

public class MainController<T> {

    public static Object lock = new Object();

    @FXML
    private AnchorPane window;

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

    @FXML
    private JFXSpinner spinWait;

    /**
     * Đây là hàm dùng để sử lý khi update -- chưa xong
     *
     * @param whatObject là String xác định đây là Object nào
     * @param event      event gọi từ function setEditCellEnable
     */
    public void handlerEventCommit(String whatObject, TableColumn.CellEditEvent event) {
        int rowChangeWhenCommit = event.getTablePosition().getRow();
        try {
            switch (whatObject) {
                case "Bộ môn":

                    // Đây là ví dụ
                    BoMon boMon = (BoMon) table.getItems().get(rowChangeWhenCommit);
                    boMon.setTen(event.getNewValue().toString());
                    allData[1].set(rowChangeWhenCommit, boMon);
                    BoMon.Update.whereId(boMon.getMa() + "", boMon);
                    // Đây là ví dụ

                    break;
                case "Đăng ký":
                    DangKy dangKy = (DangKy) table.getItems().get(rowChangeWhenCommit);
//                    String []date = event.getNewValue().toString().split("-");
//                    for (int i = 0; i < date.length; i++) {
//                        System.out.println(date[i]);
//                    }
                    Date realDate = Date.valueOf(event.getNewValue().toString());
                    dangKy.setThoiGianDangKy(realDate);

//                    System.out.println(realDate.toString());
//                    System.out.println(dangKy);
                    allData[0].set(rowChangeWhenCommit, dangKy);
                    DangKy.Update.whereId(dangKy.getMaDangKy() + "", dangKy);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Đối tượng":
                    DoiTuong doiTuong = (DoiTuong) table.getItems().get(rowChangeWhenCommit);
                    allData[2].set(rowChangeWhenCommit, doiTuong);
                    DoiTuong.Update.whereId(doiTuong.getMa() + "", doiTuong);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Giảng đường":
                    GiangDuong giangDuong = (GiangDuong) table.getItems().get(rowChangeWhenCommit);
                    allData[3].set(rowChangeWhenCommit, giangDuong);
                    GiangDuong.Update.whereId(giangDuong.getMa() + "", giangDuong);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Học phần":
                    HocPhan hocPhan = (HocPhan) table.getItems().get(rowChangeWhenCommit);
                    allData[4].set(rowChangeWhenCommit, hocPhan);
                    HocPhan.Update.whereId(hocPhan.getMa() + "", hocPhan);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Khoa":
                    Khoa khoa = (Khoa) table.getItems().get(rowChangeWhenCommit);
                    allData[5].set(rowChangeWhenCommit, khoa);
                    Khoa.Update.whereId(khoa.getMa() + "", khoa);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Môn học":
                    MonHoc monHoc = (MonHoc) table.getItems().get(rowChangeWhenCommit);
                    allData[6].set(rowChangeWhenCommit, monHoc);
                    MonHoc.Update.whereId(monHoc.getMa() + "", monHoc);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Mức thu":
                    MucThu mucThu = (MucThu) table.getItems().get(rowChangeWhenCommit);
                    allData[7].set(rowChangeWhenCommit, mucThu);
                    MucThu.Update.whereId(mucThu.getMaMucThu() + "", mucThu);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Ngành":
                    Nganh nganh = (Nganh) table.getItems().get(rowChangeWhenCommit);
                    allData[8].set(rowChangeWhenCommit, nganh);
                    Nganh.Update.whereId(nganh.getMa() + "", nganh);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
                case "Sinh viên":
                    SinhVien sinhVien = (SinhVien) table.getItems().get(rowChangeWhenCommit);
                    allData[9].set(rowChangeWhenCommit, sinhVien);
                    SinhVien.Update.whereId(sinhVien.getMaSV() + "", sinhVien);

                    // Bắt đầu làm update từ đây lưu ý thứ tự của các trường
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Hàm tiền sử lý và gọi hàm khi update
     *
     * @param whatObject  là String xác định đây là Object nào
     * @param typesOf     truyền vào mảng các đối tượng cần xác định kiểu để convert về String
     * @param tableColumn các column cần chỉnh sửa, đặt thuộc tính
     */
    public void setEditCellEnable(String whatObject, Object[] typesOf, TableColumn... tableColumn) {

        for (int i = 0; i < typesOf.length; i++) {
            // Phần này phục vụ việc edit vào bảng ---
            if (typesOf[i] instanceof Integer) {
                //convert String to value
                // Mặc định tính chất của textfield là String -> convert từ Integer sang String để code chạy hợp lệ
                tableColumn[i].setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            } else if (typesOf[i] instanceof String) {
                // Giải thích tương tự

                tableColumn[i].setCellFactory(TextFieldTableCell.forTableColumn());
            } else if (typesOf[i] instanceof Double) {
                // Giải thích tương tự

                tableColumn[i].setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            } else if (typesOf[i] instanceof Boolean) {
                // Giải thích tương tự

                tableColumn[i].setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
            } else {
                // Giải thích tương tự

                tableColumn[i].setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
            }
            tableColumn[i].setStyle("-fx-font: 16px 'Times New Roman'");
            tableColumn[i].setPrefWidth(200);
            tableColumn[i].setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                @Override
                public void handle(TableColumn.CellEditEvent event) {
//                    System.out.println(event.getNewValue().toString());
//                    System.out.println(event.getRowValue().toString());
//                    System.out.println(event.getOldValue().toString());
//                    System.out.println(event.getTablePosition().getRow());
//                    System.out.println(event.getTablePosition().getColumn());
                    Alert thongBao = new Alert(Alert.AlertType.WARNING, "Bạn có chắc chắn muốn muốn thay đổi!", ButtonType.OK, ButtonType.CANCEL);
                    if (thongBao.showAndWait().get().equals(ButtonType.OK)) {

                        handlerEventCommit(whatObject, event);
                    } else {
                        // do nothing in here
                        table.refresh();
                    }
                }
            });
        }
    }

    /**
     * Theo thứ tự allData[0] -> 10 sẽ lấy dữ liệu từ trên xuống dưới
     * vd:
     * allData[0] = danh sách đăng ký
     * allData[1] = danh sách bộ môn
     * allData[2] = danh sách đối tượng
     * allData[3] = danh sách giảng đường
     * allData[4] = danh sách học phần
     * allData[5] = danh sách khoa
     * allData[6] = danh sách môn học
     * allData[7] = danh sách mức thu
     * allData[8] = danh sách ngành
     * allData[9] = danh sách sinh viên
     * allData[10] = danh sách phiếu thu
     */
    public static List[] allData = new ArrayList[11];

    // Tạo thread gọi data từ bảng đăng ký trong csdl rồi add vào table
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

        TableColumn dk = new TableColumn("Thời gian đăng ký");
        dk.setCellValueFactory(
                new PropertyValueFactory<DangKy, String>("thoiGianDangKy")
        );
        //
        try {
            List<DangKy> list = DangKy.Search.getAll();
            allData[0] = list;

            Platform.runLater(() -> {
                        synchronized (lock) {
                            ObservableList observableList = FXCollections.observableArrayList(
                                    list
                            );
                            table.setItems(observableList);
                            table.getColumns().addAll(ma, gd, hp, dk);

                            Object[] typeOf = new Object[3];
                            typeOf[0] = (Integer) 1;
                            typeOf[1] = (Integer) 1;
                            typeOf[2] = (String) "";

                            setEditCellEnable("Đăng ký", typeOf, gd, hp, dk);
                        }
                    }
            );
            System.out.println("I'm running");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    });

    // những cái dưới tương tự cái trên
    Thread getThreadTableBoMon = new Thread(() -> {
        // Tao column cho table nhe
        TableColumn ma = new TableColumn("Mã bộ môn");
//        ma.getStyleClass().add("-fx-size: 40px");
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
//        setEditCellEnable(ma, ten);

        try {
            // cái này là đổ dữ liệu vào table
            List<BoMon> list = BoMon.Search.getAll();
            allData[1] = list;


            // Run later biết cái này chưa nói đi
            // Khi mà ông chạy 1 thread -> sang cái function này nhé
            // Nhưng mà trong lúc đấy nó lại gọi form của thread gọi tới thread này
            // => bị lõi. Cho nên phải chờ funciton này thực hiện xong nó mới quay trở lại
            // Thread ban đầu gọi tới thread này
            // Plateform.runLater thực hiện điều này (nghĩa là nó thực hiện sau khi function kết thúc)
            ///
            Platform.runLater(
                    () -> {
                        synchronized (lock) {
                            ObservableList observableList = FXCollections.observableArrayList(
                                    // getAll trả về list của của bộ môn Ctrl + Q xem định nghĩa
                                    list
                            );
                            // Đây là set cell, hay là phần tử trong bảng
                            table.setItems(observableList);
                            // Cái này là tên cột
                            Object[] typeOf = new Object[1];
                            typeOf[0] = (String) "";

                            setEditCellEnable("Bộ môn", typeOf, ten);
                            table.getColumns().addAll(ma, ten);
                        }
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

        try {
            List<DoiTuong> list = DoiTuong.Search.getAll();
            allData[2] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, ten);
                    Object[] typeOf = new Object[1];
                    typeOf[0] = (String) "";

                    setEditCellEnable("Đối tượng", typeOf, ten);
                }
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

        try {
            List<GiangDuong> list = GiangDuong.Search.getAll();
            allData[3] = list;


            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, ten);
                    Object[] typeOf = new Object[1];
                    typeOf[0] = (String) "";

                    setEditCellEnable("Giảng đường", typeOf, ten);
                }
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

        try {
            List<HocPhan> list = HocPhan.Search.getAll();
            allData[4] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, maMonHoc, soTinChi, maMucThu, giaoVienGiangDay, thoiGian);
                    Object[] typeOf = new Object[4];
                    typeOf[0] = (Integer) 1;
                    typeOf[1] = (Integer) 1;
                    typeOf[2] = (String) "";
                    typeOf[3] = (String) "";

                    setEditCellEnable("Học phần", typeOf, soTinChi, maMucThu, giaoVienGiangDay, thoiGian);
                }
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

        try {
            List<Khoa> list = Khoa.Search.getAll();
            allData[5] = list;


            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, ten);
                    Object[] typeOf = new Object[1];
                    typeOf[0] = (String) "";

                    setEditCellEnable("Khoa", typeOf, ten);
                }
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

        try {
            List<MonHoc> list = MonHoc.Search.getAll();
            allData[6] = list;


            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, ten);
                    Object[] typeOf = new Object[1];
                    typeOf[0] = (String) "";
                    setEditCellEnable("Môn học", typeOf, ten);
                }
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

        try {
            List<MucThu> list = MucThu.Search.getAll();
            allData[7] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(maMucThu, moTa, soTien);
                    Object[] typeOf = new Object[2];
                    typeOf[0] = (String) "";
                    typeOf[1] = (Double) 1.1;

                    setEditCellEnable("Mức thu", typeOf, moTa, soTien);
                }
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

        try {
            List<Nganh> list = Nganh.Search.getAll();
            allData[8] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(ma, ten);
                    Object[] typeOf = new Object[1];

                    typeOf[0] = (String) "";
                    setEditCellEnable("Ngành", typeOf, ten);
                }
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

        try {
            List<SinhVien> list = SinhVien.Search.getAll();
            allData[9] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );
                    table.setItems(observableList);
                    table.getColumns().addAll(maSV, maDT, maBoMon, tenSV, gioiTinh, ngaySinh, diaChi);
                    Object[] typeOf = new Object[6];
                    typeOf[0] = (Integer) 1;
                    typeOf[1] = (Integer) 1;
                    typeOf[2] = (String) "";
                    typeOf[3] = (String) "";
                    typeOf[4] = (String) "";
                    typeOf[5] = (String) "";

                    setEditCellEnable("Sinh viên", typeOf, maDT, maBoMon, tenSV, gioiTinh, ngaySinh, diaChi);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    Thread getThreadTablePhieuThu = new Thread(() -> {
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
            List<PhieuThu> list = PhieuThu.Search.getAll();
            allData[10] = list;

            Platform.runLater(() -> {
                synchronized (lock) {
                    ObservableList observableList = FXCollections.observableArrayList(
                            list
                    );

                    table.setItems(observableList);
                    table.getColumns().addAll(maPT, maSV, soTien, ngayBatDauThu, ngayNop, trangThai);
//                Object[] typeOf = new Object[5];
//                typeOf[0] = (Integer) 1;
//                typeOf[1] = (Long) Long.MIN_VALUE;
//                typeOf[2] = (String) "";
//                typeOf[3] = (String) "";
//                typeOf[4] = (Boolean) true;

//                setEditCellEnable("Phiếu thu", typeOf, maSV, soTien, ngayBatDauThu, ngayNop, trangThai);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    });

    //    public void stopAllThread() {
//        if(!getThreadTableBoMon.isInterrupted()) {
//            getThreadTableBoMon.interrupt();
//        } else if(!getThreadTableSinhVien.isInterrupted()){
//            getThreadTableSinhVien.interrupt();
//        } else if(!getThreadTableDangKy.isInterrupted()) {
//            getThreadTableDangKy.interrupt();
//        } else if(!getThreadTableDoiTuong.isInterrupted()) {
//            getThreadTableDoiTuong.interrupt();
//        } else if(!getThreadTableGiangDuong.isInterrupted()) {
//            getThreadTableGiangDuong.interrupt();
//        } else if(!getThreadTableHocPhan.isInterrupted()) {
//            getThreadTableHocPhan.interrupt();
//        } else if(!getThreadTablePhieuThu.isInterrupted()) {
//            getThreadTablePhieuThu.interrupt();
//        } else if(!getThreadTableNganh.isInterrupted()) {
//            getThreadTableNganh.interrupt();
//        } else if(!getThreadTableMucThu.isInterrupted()) {
//            getThreadTableMucThu.interrupt();
//        } else if(!getThreadTableMonHoc.isInterrupted()) {
//            getThreadTableMonHoc.interrupt();
//        } else if(!getThreadTableKhoa.isInterrupted()) {
//            getThreadTableKhoa.interrupt();
//        }
//    }
    ExecutorService executorService = Executors.newFixedThreadPool(11);

    /**
     * File chay khoi tao
     */
    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'Admin.fxml'.";
        assert selectTable != null : "fx:id=\"selectTable\" was not injected: check your FXML file 'Admin.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'Admin.fxml'.";
        assert repair != null : "fx:id=\"repair\" was not injected: check your FXML file 'Admin.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'Admin.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Admin.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Admin.fxml'.";


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
        spinWait.setVisible(false);
        //
//        getThreadTableBoMon.run();
        selectTable.getItems().addAll(arrayList);
        try {
            selectTable.setOnAction((e) -> {

                // chấm dứt hết các thread ở các bảng
                try {
                    executorService.awaitTermination(0, TimeUnit.SECONDS);
                } catch (InterruptedException e1) {
                    System.out.println("Hello");
                    e1.printStackTrace();
                }

                synchronized (lock) {
                    waitOfSpin();
                    Platform.runLater(() -> {
                        try {
                            switch (selectTable.getValue().toString()) {
                                case "Bộ môn":
                                    search.setPromptText("Tên bộ môn");
                                    refreshTableView();
//                            System.out.println("1");
//                            getThreadTableBoMon.start();
                                    executorService.execute(getThreadTableBoMon);
                                    break;
                                case "Đăng ký":
                                    search.setPromptText("Tên mã đăng ký");
                                    refreshTableView();
                                    executorService.execute(getThreadTableDangKy);
                                    break;
                                case "Đối tượng":
                                    search.setPromptText("Tên đối tượng");
                                    refreshTableView();
                                    executorService.execute(getThreadTableDoiTuong);
                                    break;
                                case "Giảng đường":
                                    search.setPromptText("Tên giảng đường");
                                    refreshTableView();
                                    executorService.execute(getThreadTableGiangDuong);
                                    break;
                                case "Học phần":
                                    search.setPromptText("Tên giáo viên giảng dạy");
                                    refreshTableView();
                                    executorService.execute(getThreadTableHocPhan);
                                    break;
                                case "Khoa":
                                    search.setPromptText("Tên khoa");
                                    refreshTableView();
                                    executorService.execute(getThreadTableKhoa);
                                    break;
                                case "Môn học":
                                    search.setPromptText("Tên môn học");
                                    refreshTableView();
                                    executorService.execute(getThreadTableMonHoc);
                                    break;
                                case "Mức thu":
                                    search.setPromptText("Tìm kiếm mô tả");
                                    refreshTableView();
                                    executorService.execute(getThreadTableMucThu);
                                    break;
                                case "Ngành":
                                    search.setPromptText("Tên ngành");
                                    refreshTableView();
                                    executorService.execute(getThreadTableNganh);
                                    break;
                                case "Sinh viên":
                                    search.setPromptText("Tên sinh viên");
                                    refreshTableView();
                                    executorService.execute(getThreadTableSinhVien);
                                    break;
                                case "Phiếu thu":
                                    search.setPromptText("Tìm kiếm mã sinh viên");
                                    refreshTableView();
                                    executorService.execute(getThreadTablePhieuThu);
                                    break;
                            }
                        } catch (Exception exc) {
                            System.out.println("No NO");
                            System.out.println(exc.getCause());
                        } finally {
                            // run into current thread ...
//                    new Thread(() -> {
//                        long currentTime = System.currentTimeMillis();
//                        while(true) {
//                            if(System.currentTimeMillis() - currentTime > 100) {
//                                break;
//                            }
//                        }
//                    }).run();
                            System.out.println("Hello world");
                            submitOfSpin();
                        }
                    });
                }
            });
        } catch (Exception e) {
            System.out.println("Hello you got some issue " + e.getMessage());
            refreshTableView();
        }
//        selectTable.getSelectionModel().select(0);
//        refreshTableView();
//        System.out.println(table.isEditable());
    }

    private void waitOfSpin() {
        spinWait.setVisible(true);
        table.setVisible(false);
    }

    private void submitOfSpin() {
        spinWait.setVisible(false);
        table.setVisible(true);
    }

    /**
     * Dùng để làm mới bảng khi chuyển đối tượng đổ dữ liệu
     */
    private void refreshTableView() {
//        table.getItems().clear();
        table.getColumns().clear();
        table.refresh();
    }


    /**
     * Phần thêm ở đây -- chưa xong
     *
     * @throws IOException
     */
    public void onAddButtonClicked() throws IOException, InterruptedException {
        Platform.runLater(() -> {

            Stage secondaryStage = new Stage();
            JFXAlert alert = null;
            secondaryStage.setResizable(false);
            switch (selectTable.getValue().toString()) {
                case "Bộ môn":


                    secondaryStage.setTitle("Thêm của bộ môn");
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../view/InsertBoMon.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    secondaryStage.setScene(new Scene(root, 462, 294));
                    alert = new JFXAlert(secondaryStage);
                    alert.setGraphic(root);

                    // nếu là bộ môn
                    break;
                case "Đăng ký":

                    break;
                case "Đối tượng":

                    break;
                case "Giảng đường":

                    break;
                case "Học phần":

                    break;
                case "Khoa":

                    break;
                case "Môn học":

                    break;
                case "Mức thu":

                    break;
                case "Ngành":

                    break;
                case "Sinh viên":

                    break;
                case "Phiếu thu":

                    break;
            }

//        secondaryStage.showAndWait();

            alert.showAndWait();
            table.getItems().clear();
            table.getItems().addAll(
                allData[1]
            );
        });
    }

    /**
     * Xóa đã xong
     */
    public void onDeleteButtonClicked() {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có thực sự muốn xóa?");
        info.showAndWait();
        if (info.getResult() == ButtonType.OK) {
            Platform.runLater(() -> {
                switch (selectTable.getValue().toString()) {
                    case "Bộ môn":
                        BoMon boMon = (BoMon) table.getSelectionModel().getSelectedItem();
                        BoMon.Delete.whereId("" + boMon.getMa());
                        break;

                    case "Đăng ký":
                        DangKy dangKy = (DangKy) table.getSelectionModel().getSelectedItem();
                        DangKy.Delete.whereId(dangKy.getMaDangKy());
                        break;

                    case "Đối tượng":
                        DoiTuong doiTuong = (DoiTuong) table.getSelectionModel().getSelectedItem();
                        DoiTuong.Delete.whereId("" + doiTuong.getMa());
                        break;

                    case "Giảng đường":
                        GiangDuong giangDuong = (GiangDuong) table.getSelectionModel().getSelectedItem();
                        GiangDuong.Delete.whereId("" + giangDuong.getMa());
                        break;

                    case "Học phần":
                        HocPhan hocPhan = (HocPhan) table.getSelectionModel().getSelectedItem();
                        HocPhan.Delete.whereId("" + hocPhan.getMa());
                        break;

                    case "Khoa":
                        Khoa khoa = (Khoa) table.getSelectionModel().getSelectedItem();
                        Khoa.Delete.whereId("" + khoa.getMa());
                        break;

                    case "Môn học":
                        MonHoc monHoc = (MonHoc) table.getSelectionModel().getSelectedItem();
                        MonHoc.Delete.whereId("" + monHoc.getMa());
                        break;

                    case "Mức thu":
                        MucThu mucThu = (MucThu) table.getSelectionModel().getSelectedItem();
                        MucThu.Delete.whereId("" + mucThu.getMaMucThu());
                        break;
                    case "Ngành":
                        Nganh nganh = (Nganh) table.getSelectionModel().getSelectedItem();
                        MucThu.Delete.whereId("" + nganh.getMa());
                        break;
                    case "Sinh viên":
                        SinhVien sinhVien = (SinhVien) table.getSelectionModel().getSelectedItem();
                        SinhVien.Delete.whereId("" + sinhVien.getMaSV());
                        break;

                }
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
                new Alert(Alert.AlertType.INFORMATION, "Xóa thành công!!!").showAndWait();
            });

        }
    }

    /**
     * Tìm kiếm đã xong
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void actionSearch(ActionEvent event) throws SQLException {
        Platform.runLater(() -> {
            try {
                ObservableList filterData = null;
                switch (selectTable.getValue().toString()) {
                    case "Bộ môn":
                        filterData = FXCollections.observableArrayList(
                                BoMon.Search.getAll()
                        ).filtered(e -> {
                            return ((BoMon) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Đăng ký":
                        filterData = FXCollections.observableArrayList(
                                DangKy.Search.getAll()
                        ).filtered(e -> {
                            return ((DangKy) e).
                                    getMaDangKy().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Đối tượng":
                        filterData = FXCollections.observableArrayList(
                                DoiTuong.Search.getAll()
                        ).filtered(e -> {
                            return ((DoiTuong) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Giảng đường":
                        filterData = FXCollections.observableArrayList(
                                GiangDuong.Search.getAll()
                        ).filtered(e -> {
                            return ((GiangDuong) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Học phần":
                        filterData = FXCollections.observableArrayList(
                                HocPhan.Search.getAll()
                        ).filtered(e -> {
                            return ((HocPhan) e).
                                    getGiaoVienGiangDay().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Khoa":
                        filterData = FXCollections.observableArrayList(
                                Khoa.Search.getAll()
                        ).filtered(e -> {
                            return ((Khoa) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Môn học":
                        filterData = FXCollections.observableArrayList(
                                MonHoc.Search.getAll()
                        ).filtered(e -> {
                            return ((MonHoc) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Mức thu":
                        filterData = FXCollections.observableArrayList(
                                MucThu.Search.getAll()
                        ).filtered(e -> {
                            return ((MucThu) e).
                                    getMoTa().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Ngành":
                        filterData = FXCollections.observableArrayList(
                                Nganh.Search.getAll()
                        ).filtered(e -> {
                            return ((Nganh) e).
                                    getTen().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Sinh viên":
                        filterData = FXCollections.observableArrayList(
                                SinhVien.Search.getAll()
                        ).filtered(e -> {
                            return ((SinhVien) e).
                                    getTenSV().toLowerCase().
                                    contains(search.getText().toLowerCase());
                        });
                        break;
                    case "Phiếu thu":
                        filterData = FXCollections.observableArrayList(
                                PhieuThu.Search.getAll()
                        ).filtered(e -> {
                            return ((Integer) (((PhieuThu) e).
                                    getMaSV())).toString().contains(search.getText());
                        });
                        break;
                }

                table.setItems(filterData);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}