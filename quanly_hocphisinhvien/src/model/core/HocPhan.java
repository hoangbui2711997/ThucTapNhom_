package model.core;

import model.database.QueryDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HocPhan {
    private int ma, maMucThu, maMonHoc;
    private byte soTinChi;
    private String ten, giaoVienGiangDay;
    private Date thoiGian;
    private List<DangKy> dsHocPhan;

    public HocPhan(int ma, int maMucThu, int maMonHoc, byte soTinChi, String ten, String giaoVienGiangDay, Date thoiGian) {
        this.ma = ma;
        this.maMucThu = maMucThu;
        this.maMonHoc = maMonHoc;
        this.soTinChi = soTinChi;
        this.ten = ten;
        this.giaoVienGiangDay = giaoVienGiangDay;
        this.thoiGian = thoiGian;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaMucThu() {
        return maMucThu;
    }

    public void setMaMucThu(int maMucThu) {
        this.maMucThu = maMucThu;
    }

    public int getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public byte getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(byte soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiaoVienGiangDay() {
        return giaoVienGiangDay;
    }

    public void setGiaoVienGiangDay(String giaoVienGiangDay) {
        this.giaoVienGiangDay = giaoVienGiangDay;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public List<DangKy> getDsHocPhan() {
        return dsHocPhan;
    }

    public void setDsHocPhan(List<DangKy> dsHocPhan) {
        this.dsHocPhan = dsHocPhan;
    }

    public static HocPhan id(int maHocPhan) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM HOCPHAN WHERE mahocphan = " + maHocPhan);
        resultSet.next();

        return queryDB.getHocPhan(resultSet);
    }
}
