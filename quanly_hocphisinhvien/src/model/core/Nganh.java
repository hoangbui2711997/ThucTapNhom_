package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Nganh {
    private int ma;
    private String ten;
    private List<BoMon> dsBoMon;

    public Nganh(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<BoMon> getDsBoMon() {
        return dsBoMon;
    }

    public void setDsBoMon(List<BoMon> dsBoMon) {
        this.dsBoMon = dsBoMon;
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

    public static Nganh id(int maNganh) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM NGANH WHERE manganh = " + maNganh);
        resultSet.next();

        return queryDB.getNganh(resultSet);
    }
}
