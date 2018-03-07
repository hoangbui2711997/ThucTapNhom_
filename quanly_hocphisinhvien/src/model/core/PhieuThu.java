package model.core;

import model.database.QueryDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuThu {
    private int ma, maSV;
    private long soTien;
    private Date ngayBatDauThu, ngayNop;
    private boolean trangThai;

    public PhieuThu(int ma, int maSV, long soTien, Date ngayBatDauThu, Date ngayNop, boolean trangThai) {
        this.ma = ma;
        this.maSV = maSV;
        this.soTien = soTien;
        this.ngayBatDauThu = ngayBatDauThu;
        this.ngayNop = ngayNop;
        this.trangThai = trangThai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public Date getNgayBatDauThu() {
        return ngayBatDauThu;
    }

    public void setNgayBatDauThu(Date ngayBatDauThu) {
        this.ngayBatDauThu = ngayBatDauThu;
    }

    public Date getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public static PhieuThu id(int maPhieuThu) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM PHIEUTHU WHERE mapt = " + maPhieuThu);
        resultSet.next();

        return queryDB.getPhieuThu(resultSet);
    }
}
