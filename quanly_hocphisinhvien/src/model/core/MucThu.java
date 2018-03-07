package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MucThu {

    private int maMucThu;
    private String moTa;
    private long soTien;
    private List<HocPhan> dsHocPhan;

    public MucThu(int maMucThu, String moTa, long soTien) {
        this.maMucThu = maMucThu;
        this.moTa = moTa;
        this.soTien = soTien;
    }

    public int getMaMucThu() {
        return maMucThu;
    }

    public void setMaMucThu(int maMucThu) {
        this.maMucThu = maMucThu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public List<HocPhan> getDsHocPhan() {
        return dsHocPhan;
    }

    public void setDsHocPhan(List<HocPhan> dsHocPhan) {
        this.dsHocPhan = dsHocPhan;
    }

    public static MucThu id(int maMucThu) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM MUCTHU WHERE mamucthu = " + maMucThu);
        resultSet.next();

        return queryDB.getMucThu(resultSet);
    }
}
