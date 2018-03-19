package model.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DangKy {
    private SimpleStringProperty maDangKy;
    private SimpleIntegerProperty maGiangDuong, maHocPhan, maSinhVien;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private SimpleStringProperty thoiGianDangKy;
    private static String statement;


    public static DangKy getInstanceID(String maDangKy, int maGiangDuong, int maHocPhan, int maSinhVien, String thoiGianDangKy) {
        return new DangKy(maDangKy, maGiangDuong, maHocPhan, maSinhVien, thoiGianDangKy);
    }

    private DangKy(String maDangKy, int maGiangDuong, int maHocPhan, int maSinhVien, String thoiGianDangKy) {
        this.maDangKy = new SimpleStringProperty(maDangKy);
        this.maGiangDuong = new SimpleIntegerProperty(maGiangDuong);
        this.maHocPhan = new SimpleIntegerProperty(maHocPhan);
        this.maSinhVien = new SimpleIntegerProperty(maSinhVien);
        this.thoiGianDangKy = new SimpleStringProperty(thoiGianDangKy);
    }

    public DangKy(int maGiangDuong, int maHocPhan, int maSinhVien, String thoiGianDangKy) {
        this.maGiangDuong = new SimpleIntegerProperty(maGiangDuong);
        this.maHocPhan = new SimpleIntegerProperty(maHocPhan);
        this.maSinhVien = new SimpleIntegerProperty(maSinhVien);
        this.thoiGianDangKy = new SimpleStringProperty(thoiGianDangKy);

    }

    public String getMaDangKy() {
        return maDangKy.getValue();
    }

    public int getMaGiangDuong() {
        return maGiangDuong.getValue();
    }

    public void setMaGiangDuong(int maGiangDuong) {
        this.maGiangDuong.setValue(maGiangDuong);
    }

    public int getMaHocPhan() {
        return maHocPhan.getValue();
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan.setValue(maHocPhan);
    }

    public int getMaSinhVien() {
        return maSinhVien.getValue();
    }

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien.setValue(maSinhVien);
    }

    public String getThoiGianDangKy() {
        return this.thoiGianDangKy.getValue();
    }

    public void setThoiGianDangKy(Date thoiGianDangKy) {
        this.thoiGianDangKy.setValue(thoiGianDangKy.toString());
    }

    public static class Search {
        private Search() {
        }

        public static DangKy where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM DANGKY WHERE " + where);
            resultSet.next();

            return searchDB.getDK(resultSet);
        }

        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<DangKy> getAll() throws SQLException {
            return searchDB.getDsDangKy();
        }
    }


    public static DangKy Insert(DangKy dangKy) throws SQLException {
        try {

            String id = InsertDB.getInstance().initInsert("DANGKY") + "";

            statement = "INSERT INTO DANGKY(magd, mahp, masv, thoigiandk) VALUES(" +
//                    dangKy.getMaDangKy() + ", " +
                    dangKy.getMaGiangDuong() + ", " +
                    dangKy.getMaHocPhan() +", " +
                    dangKy.getMaSinhVien() +", " +
                    "'" + dangKy.getThoiGianDangKy() + "'" +
                    ")";
            // wait form input
            // wait form input
            // wait form input

//            DangKy.Update.where("madk = " + id, new DangKy(id, dangKy.getMaGiangDuong(),
//                    dangKy.getMaHocPhan(), dangKy.getMaSinhVien(), dangKy.getThoiGianDangKy()));
            InsertDB.getInstance().insertCommand(statement);
            return new DangKy(id, dangKy.getMaGiangDuong(), dangKy.getMaHocPhan(),
                    dangKy.getMaSinhVien(), dangKy.getThoiGianDangKy());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static class Delete {

        /**
         * @param where DK Xóa
         * @return
         */
        public static Boolean where(String where) {
            try {
                statement = "DELETE FROM DANGKY WHERE madk = " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "DangKy{" +
                "maDangKy=" + maDangKy +
                ", maGiangDuong=" + maGiangDuong +
                ", maHocPhan=" + maHocPhan +
                ", maSinhVien=" + maSinhVien +
                ", thoiGianDangKy=" + thoiGianDangKy +
                '}';
    }

    public static class Update {

        /**
         * @param where     DK - update
         * @param newDangKy DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, DangKy newDangKy) throws SQLException {
            try {
                statement = "UPDATE DANGKY " +
                        "SET " +
//                        "madk = " + newDangKy.getMaDangKy() + ", " +
                        "tengd = " + newDangKy.getMaGiangDuong() + ", " +
                        "mahp = " + newDangKy.getMaHocPhan() + ", " +
                        "masv = " + newDangKy.getMaSinhVien() + ", " +
                        "thoigiandk = '" + newDangKy.getThoiGianDangKy() + "' " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
