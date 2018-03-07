package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Khoa {
    private int ma;
    private String ten;
    private List<Nganh> dsNganh;

    public Khoa(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<Nganh> getDsNganh() {
        return dsNganh;
    }

    public void setDsNganh(List<Nganh> dsNganh) {
        this.dsNganh = dsNganh;
    }

    public static Khoa id(int maKhoa) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM HOCPHAN WHERE makhoa = " + maKhoa);
        resultSet.next();

        return queryDB.getKhoa(resultSet);
    }
}
