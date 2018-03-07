package model.core;

import model.database.QueryDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SinhVien {
    private List<PhieuThu> dsPhieuThu;
    private int maSV, maDT, maBoMon;
    private String tenSV, diaChi;
    private boolean gioiTinh;
    private Date ngaySinh;

    public SinhVien(int maSV, int maDT, int maBoMon, String tenSV, String diaChi, boolean gioiTinh, Date ngaySinh) {
        this.maSV = maSV;
        this.maDT = maDT;
        this.maBoMon = maBoMon;
        this.tenSV = tenSV;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public List<PhieuThu> getDsPhieuThu() {
        return dsPhieuThu;
    }

    public void setDsPhieuThu(List<PhieuThu> dsPhieuThu) {
        this.dsPhieuThu = dsPhieuThu;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public int getMaDT() {
        return maDT;
    }

    public void setMaDT(int maDT) {
        this.maDT = maDT;
    }

    public int getMaBoMon() {
        return maBoMon;
    }

    public void setMaBoMon(int maBoMon) {
        this.maBoMon = maBoMon;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public static SinhVien id(int maSinhVien) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM SINHVIEN WHERE masv = " + maSinhVien);
        resultSet.next();

        return queryDB.getSV(resultSet);
    }
}
