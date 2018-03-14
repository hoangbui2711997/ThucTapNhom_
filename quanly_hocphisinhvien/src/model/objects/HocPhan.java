package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HocPhan {
    private int ma, maMucThu, maMonHoc;
    private byte soTinChi;
    private String giaoVienGiangDay;
    private Date thoiGian;
    private List<DangKy> dsHocPhan;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public HocPhan(int maMonHoc, byte soTinChi, int maMucThu, String giaoVienGiangDay, Date thoiGian) {
        this.maMucThu = maMucThu;
        this.maMonHoc = maMonHoc;
        this.soTinChi = soTinChi;
        this.giaoVienGiangDay = giaoVienGiangDay;
        this.thoiGian = thoiGian;
    }

    private HocPhan(int ma, int maMonHoc, byte soTinChi, int maMucThu, String giaoVienGiangDay, Date thoiGian) {
        this.ma = ma;
        this.maMucThu = maMucThu;
        this.maMonHoc = maMonHoc;
        this.soTinChi = soTinChi;
        this.giaoVienGiangDay = giaoVienGiangDay;
        this.thoiGian = thoiGian;
    }

    public static HocPhan getInstanceID(int ma, int maMonHoc, byte soTinChi, int maMucThu, String giaoVienGiangDay, Date thoiGian) {
        return new HocPhan(ma, maMonHoc, soTinChi, maMucThu, giaoVienGiangDay, thoiGian);
    }

    public int getMa() {
        return ma;
    }

    public int getMaMucThu() {
        return maMucThu;
    }

    public void setMaMucThu(int maMucThu) {
        this.maMucThu = maMucThu;
    }

    public int getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public byte getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(byte soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getGiaoVienGiangDay() {
        return giaoVienGiangDay;
    }

    public void setGiaoVienGiangDay(String giaoVienGiangDay) {
        this.giaoVienGiangDay = giaoVienGiangDay;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public List<DangKy> getDsHocPhan() {
        return dsHocPhan;
    }

    public void setDsHocPhan(List<DangKy> dsHocPhan) {
        this.dsHocPhan = dsHocPhan;
    }

    public static class Search {
        private Search() {
        }

        public static HocPhan where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM HOCPHAN WHERE " + where);
            resultSet.next();

            return searchDB.getHocPhan(resultSet);
        }
    }

    /**
     * @return Lay tat ca sinh vien trong csdl
     * @throws SQLException
     */
    public static List<HocPhan> getAll() throws SQLException {
        return searchDB.getDsHocPhan();
    }

    public static HocPhan Insert(HocPhan hocPhan) throws SQLException {
        try {
//            statement = "INSERT INTO HOCPHAN(mahp, mamonhoc, sotinchi, mamucthu, gvgd, thoigian) VALUES" +
//                    "(" +
//                    hocPhan.getMa() + ", " +
//                    hocPhan.getMaMonHoc() + ", " +
//                    hocPhan.getSoTinChi() + ", " +
//                    hocPhan.getMaMucThu() + ", " +
//                    "N'"+ hocPhan.getGiaoVienGiangDay() + "', " +
//                    "'" + hocPhan.getThoiGian() +"'" +
//                    ")";
            int id = InsertDB.getInstance().initInsert("HOCPHAN");

            // wait form input
            // wait form input
            // wait form input

//            HocPhan.Update.where("mahp = " + id, new HocPhan(id, hocPhan.getMaMonHoc(), hocPhan.getSoTinChi(),
//                    hocPhan.getMaMucThu(), hocPhan.getGiaoVienGiangDay(), hocPhan.getThoiGian()));
            InsertDB.getInstance().insertCommand(statement);
            return new HocPhan(id, hocPhan.maMonHoc, hocPhan.soTinChi, hocPhan.maMucThu, hocPhan.giaoVienGiangDay, hocPhan.thoiGian);
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
                statement = "DELETE HOCPHAN WHERE " + where;
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
        return "HocPhan{" +
                "ma=" + ma +
                ", maMucThu=" + maMucThu +
                ", maMonHoc=" + maMonHoc +
                ", soTinChi=" + soTinChi +
                ", giaoVienGiangDay='" + giaoVienGiangDay + '\'' +
                ", thoiGian=" + thoiGian +
                '}';
    }

    public static class Update {

        /**
         * @param where      DK - update
         * @param newHocPhan DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, HocPhan newHocPhan) throws SQLException {
            try {
                statement = "UPDATE HOCPHAN SET " +
//                        "mahp = " + newHocPhan.getMa() + ", " +
                        "mamonhoc = " + newHocPhan.getMaMonHoc() + ", " +
                        "sotinchi = " + newHocPhan.getSoTinChi() + ", " +
                        "mamucthu = " + newHocPhan.getMaMucThu() + ", " +
                        "gvgd = N'" + newHocPhan.getGiaoVienGiangDay() + "', " +
                        "thoigian = '" + newHocPhan.getThoiGian() + "' " +
                        "where " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
