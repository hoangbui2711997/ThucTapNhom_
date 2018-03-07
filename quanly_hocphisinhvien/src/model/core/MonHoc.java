package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonHoc {
    private int ma;
    private String ten;

    public MonHoc(int ma, String ten) {
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

    public static MonHoc id(int maMonHoc) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM MONHOC WHERE mamonhoc = " + maMonHoc);
        resultSet.next();

        return queryDB.getMonHoc(resultSet);
    }
}
