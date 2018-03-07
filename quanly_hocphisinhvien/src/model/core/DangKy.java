package model.core;

import model.database.QueryDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangKy {
    private int maDangKy, maGiangDay, maHocPhan, maSinhVien;
    Date thoiGianDangKy;

    public DangKy(int maDangKy, int maGiangDay, int maHocPhan, int maSinhVien, Date thoiGianDangKy) {
        this.maDangKy = maDangKy;
        this.maGiangDay = maGiangDay;
        this.maHocPhan = maHocPhan;
        this.maSinhVien = maSinhVien;
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public int getMaDangKy() {
        return maDangKy;
    }

    public void setMaDangKy(int maDangKy) {
        this.maDangKy = maDangKy;
    }

    public int getMaGiangDay() {
        return maGiangDay;
    }

    public void setMaGiangDay(int maGiangDay) {
        this.maGiangDay = maGiangDay;
    }

    public int getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public int getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public Date getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(Date thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public static DangKy id(int maDangKy) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM DANGKY WHERE madangky = " + maDangKy);
        resultSet.next();

        return queryDB.getDK(resultSet);
    }
}
