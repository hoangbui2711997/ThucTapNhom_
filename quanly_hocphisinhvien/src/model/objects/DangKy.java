package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DangKy {
    private int maDangKy, maGiangDuong, maHocPhan, maSinhVien;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    Date thoiGianDangKy;
    private static String statement;


    public static DangKy getInstanceID(int maDangKy, int maGiangDuong, int maHocPhan, int maSinhVien, Date thoiGianDangKy) {
        return new DangKy(maDangKy, maGiangDuong, maHocPhan, maSinhVien, thoiGianDangKy);
    }

    private DangKy(int maDangKy, int maGiangDuong, int maHocPhan, int maSinhVien, Date thoiGianDangKy) {
        this.maDangKy = maDangKy;
        this.maGiangDuong = maGiangDuong;
        this.maHocPhan = maHocPhan;
        this.maSinhVien = maSinhVien;
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public DangKy(int maGiangDuong, int maHocPhan, int maSinhVien, Date thoiGianDangKy) {
        this.maDangKy = maDangKy;
        this.maGiangDuong = maGiangDuong;
        this.maHocPhan = maHocPhan;
        this.maSinhVien = maSinhVien;
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public int getMaDangKy() {
        return maDangKy;
    }

    public int getMaGiangDuong() {
        return maGiangDuong;
    }

    public void setMaGiangDuong(int maGiangDuong) {
        this.maGiangDuong = maGiangDuong;
    }

    public int getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public int getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public Date getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(Date thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public static class Search {
        private Search() {
        }

        public static DangKy where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM DANGKY WHERE " + where);
            resultSet.next();

            return searchDB.getDK(resultSet);
        }
    }

    /**
     * @return Lay tat ca sinh vien trong csdl
     * @throws SQLException
     */
    public static List<DangKy> getAll() throws SQLException {
        return searchDB.getDsDangKy();
    }


    public static DangKy Insert(DangKy dangKy) throws SQLException {
        try {

            int id = InsertDB.getInstance().initInsert("DANGKY");

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
                statement = "DELETE DANGKY WHERE " + where;
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
