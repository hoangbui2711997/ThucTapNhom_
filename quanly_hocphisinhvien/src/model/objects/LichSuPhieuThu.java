package model.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.InsertDB;
import model.database.SearchDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LichSuPhieuThu {

    private SimpleIntegerProperty masv;
    private SimpleStringProperty maDangKy;
    private static String statement = "";
    private static SearchDB searchDB = SearchDB.getQueryDB();
    public LichSuPhieuThu(int masv, String maDangKy) {
        this.masv = new SimpleIntegerProperty(masv);
        this.maDangKy = new SimpleStringProperty(maDangKy);
    }

    public int getMasv() {
        return masv.get();
    }

    public SimpleIntegerProperty masvProperty() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv.set(masv);
    }

    public String getMaDangKy() {
        return maDangKy.get();
    }

    public SimpleStringProperty maDangKyProperty() {
        return maDangKy;
    }

    public void setMaDangKy(String maDangKy) {
        this.maDangKy.set(maDangKy);
    }

    public static Boolean Insert(LichSuPhieuThu lichSuPhieuThu) throws SQLException {
        if(lichSuPhieuThu != null) {
            statement = "INSERT INTO LICHSUDANGKY VALUES" +
                    "(" +
//                    phieuThu.getMa() + ", " +
                    lichSuPhieuThu.getMasv() + ", " +
                    "'" + lichSuPhieuThu.getMaDangKy() + "'" +
                    ")";

            InsertDB.getInstance().insertCommand(statement);
            return true;
        }
        return false;
    }

    public static LichSuPhieuThu getInstanceID(Integer mabm, String tenbm) {
        return new LichSuPhieuThu(mabm, tenbm);
    }

    public static class Search {
        private Search() {
        }

        public static PhieuThu where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM LICHSUPHIEUTHU WHERE " + where);
            resultSet.next();

            return searchDB.getPhieuThu(resultSet);
        }

        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<PhieuThu> getAll() throws SQLException {
            return searchDB.getDsPhieuThu();
        }

        public static PhieuThu whereId(String id) throws SQLException {
            return where("masv = " + id);
        }
    }
}
