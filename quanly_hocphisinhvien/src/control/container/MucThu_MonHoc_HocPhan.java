package control.container;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DB_Connection;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MucThu_MonHoc_HocPhan {
    private SimpleIntegerProperty maHocPhan, soTinChi;
    private SimpleDoubleProperty mucThu, soTien;
    private SimpleStringProperty monHoc, giaoVienGiangDay, thoiGian, tenGD, maDangKy;

    public int getMaHocPhan() {
        return maHocPhan.get();
    }

    public SimpleIntegerProperty maHocPhanProperty() {
        return maHocPhan;
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan.set(maHocPhan);
    }

    public int getSoTinChi() {
        return soTinChi.get();
    }

    public SimpleIntegerProperty soTinChiProperty() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi.set(soTinChi);
    }

    public double getMucThu() {
        return mucThu.get();
    }

    public SimpleDoubleProperty mucThuProperty() {
        return mucThu;
    }

    public void setMucThu(double mucThu) {
        this.mucThu.set(mucThu);
    }

    public double getSoTien() {
        return soTien.get();
    }

    public SimpleDoubleProperty soTienProperty() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien.set(soTien);
    }

    public String getMonHoc() {
        return monHoc.get();
    }

    public SimpleStringProperty monHocProperty() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc.set(monHoc);
    }

    public String getGiaoVienGiangDay() {
        return giaoVienGiangDay.get();
    }

    public SimpleStringProperty giaoVienGiangDayProperty() {
        return giaoVienGiangDay;
    }

    public void setGiaoVienGiangDay(String giaoVienGiangDay) {
        this.giaoVienGiangDay.set(giaoVienGiangDay);
    }

    public String getThoiGian() {
        return thoiGian.get();
    }

    public SimpleStringProperty thoiGianProperty() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian.set(thoiGian);
    }

    public String getTenGD() {
        return tenGD.get();
    }

    public SimpleStringProperty tenGDProperty() {
        return tenGD;
    }

    public void setTenGD(String tenGD) {
        this.tenGD.set(tenGD);
    }

    public String getMaDangKy() {
        return maDangKy.get();
    }

    public SimpleStringProperty maDangKyProperty() {
        return maDangKy;
    }

    public void setMaDangKy(String maDangKy) {
        this.maDangKy.set(maDangKy);
    }

    public MucThu_MonHoc_HocPhan(String maDangKy, int maHocPhan, String monHoc, String giaoVienGiangDay, short soTinChi, double mucThu,
                                 double soTien, String thoiGian, String tenGD) {
        this.maDangKy = new SimpleStringProperty(maDangKy);
        this.maHocPhan = new SimpleIntegerProperty(maHocPhan);
        this.soTinChi = new SimpleIntegerProperty(soTinChi);
        this.mucThu = new SimpleDoubleProperty(mucThu);
        this.monHoc = new SimpleStringProperty(monHoc);
        this.giaoVienGiangDay = new SimpleStringProperty(giaoVienGiangDay);
        this.thoiGian = new SimpleStringProperty(thoiGian);
        this.soTien = new SimpleDoubleProperty(soTien);
        this.tenGD = new SimpleStringProperty(tenGD);
    }

    public static MucThu_MonHoc_HocPhan getThisObject(ResultSet resultSet) throws SQLException {
        Object mahp = resultSet.getObject(1);
        Object tenMonHoc  = resultSet.getObject(2);
        Object gvgd = resultSet.getObject(3);
        Object soTinChi = resultSet.getObject(5);
        Object soTienMotTin = resultSet.getObject(6);
        Object tongTien = resultSet.getObject(7);
        Object thoiGian = resultSet.getObject(8);
        Object tengd = resultSet.getObject(4);
        Object madk = resultSet.getObject(9);

        MucThu_MonHoc_HocPhan mucThu_monHoc_hocPhan =
                new MucThu_MonHoc_HocPhan((String) madk, (Integer) mahp, (String) tenMonHoc, (String) gvgd == null ? "" : (String) gvgd,
                        (Short) soTinChi,((BigDecimal) soTienMotTin).doubleValue(), ((BigDecimal) tongTien).doubleValue(),
                        ((Date) thoiGian).toString(), (String) tengd
                );

        return mucThu_monHoc_hocPhan;
    }

    public static List<MucThu_MonHoc_HocPhan> getAll() throws SQLException {
        List<MucThu_MonHoc_HocPhan> mucThu_monHoc_hocPhans = new ArrayList<>();
        ResultSet resultSet = DB_Connection.getConnection().prepareStatement("" +
                "SELECT hp.mahp, tenmonhoc, gvgd,  gd.tengd, sotinchi, sotien, sotien * sotinchi, hp.thoigian, madk " +
                "FROM HOCPHAN hp, MUCTHU mt, MONHOC mh, GIANGDUONG gd, DANGKY dk " +
                "where dk.mahp = hp.mahp and hp.mamucthu = mt.mamucthu and " +
                "hp.mamonhoc = mh.mamonhoc and gd.magd = dk.magd"
        ).executeQuery();
        while(resultSet.next()) {
            mucThu_monHoc_hocPhans.add(getThisObject(resultSet));
        }

        return mucThu_monHoc_hocPhans;
    }

    public static List<MucThu_MonHoc_HocPhan> getHistory(String id) throws SQLException {
        List<MucThu_MonHoc_HocPhan> mucThu_monHoc_hocPhans = new ArrayList<>();
        ResultSet resultSet = DB_Connection.getConnection().prepareStatement("" +
                "SELECT hp.mahp, tenmonhoc, gvgd,  gd.tengd, sotinchi, sotien, sotien * sotinchi, hp.thoigian, madk " +
                "FROM HOCPHAN hp, MUCTHU mt, MONHOC mh, GIANGDUONG gd, DANGKY dk, LICHSUDANGKY ls " +
                "where dk.mahp = hp.mahp and dk.magd = gd.magd and hp.mamonhoc = mh.mamonhoc and " +
                "hp.mamucthu = mt.mamucthu and ls.madangky = dk.madk and ls.masv = " + id
        ).executeQuery();
        while(resultSet.next()) {
            mucThu_monHoc_hocPhans.add(getThisObject(resultSet));
        }

        return mucThu_monHoc_hocPhans;
    }

    public static MucThu_MonHoc_HocPhan getId(String id) throws SQLException {
        ResultSet resultSet = DB_Connection.getConnection().prepareStatement("" +
                "SELECT hp.mahp, tenmonhoc, gvgd,  gd.tengd, sotinchi, sotien, sotien * sotinchi, hp.thoigian, madk " +
                "FROM HOCPHAN hp, MUCTHU mt, MONHOC mh, GIANGDUONG gd, DANGKY dk " +
                "where dk.mahp = hp.mahp and hp.mamucthu = mt.mamucthu and " +
                "hp.mamonhoc = mh.mamonhoc and gd.magd = dk.magd and madk = " + id
        ).executeQuery();

        resultSet.next();

        return getThisObject(resultSet);
    }
}
