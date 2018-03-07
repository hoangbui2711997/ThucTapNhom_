package model.core;

import model.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GiangDuong {
    private int ma;
    private String ten;
    private List<DangKy> dsDangKy = null;

    public GiangDuong(int ma, String ten) {
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

    public List<DangKy> getDsDangKy() {
        return dsDangKy;
    }

    public void setDsDangKy(List<DangKy> dsDangKy) {
        this.dsDangKy = dsDangKy;
    }

    public static GiangDuong id(int maGiangDuong) throws SQLException {
        QueryDB queryDB = new QueryDB();
        ResultSet resultSet = queryDB.prepareStatement("SELECT * FROM GIANGDUONG WHERE magiangduong = " + maGiangDuong);
        resultSet.next();

        return queryDB.getGiangDuong(resultSet);
    }
}
