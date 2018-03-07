package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoiTuong {
    private List<SinhVien> dsSV;
    private int ma;
    private String ten;

    public DoiTuong(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<SinhVien> getDsSV() {

        return dsSV;
    }

    public void setDsSV(List<SinhVien> dsSV) {
        this.dsSV = dsSV;
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

    public static DoiTuong id(int maDoiTuong) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM DOITUONG WHERE madoituong = " + maDoiTuong);
        resultSet.next();

        return queryDB.getDoiTuong(resultSet);
    }
}
