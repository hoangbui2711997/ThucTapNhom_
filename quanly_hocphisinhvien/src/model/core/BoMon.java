package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoMon {

    private int ma;
    private String ten;
    private List<SinhVien> dsSV = null;


    public List<SinhVien> getDsSV() {
        return dsSV;
    }

    public void setDsSV(List<SinhVien> dsSV) {
        this.dsSV = dsSV;
    }

    public BoMon(int ma, String ten) {
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

    public static BoMon id(int maBoMon) throws SQLException{
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM BOMON WHERE mabomon = " + maBoMon);
        resultSet.next();

        return queryDB.getBoMon(resultSet);
    }

}
