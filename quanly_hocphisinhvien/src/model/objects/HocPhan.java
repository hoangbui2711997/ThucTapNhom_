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
import java.util.List;

public class HocPhan {
    private SimpleIntegerProperty ma, maMucThu, maMonHoc;
    private SimpleIntegerProperty soTinChi;
    private SimpleStringProperty giaoVienGiangDay;
    private SimpleStringProperty thoiGian;
    private List<DangKy> dsHocPhan;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public HocPhan(int maMonHoc, short soTinChi, int maMucThu, String giaoVienGiangDay, String thoiGian) {
        this.maMucThu = new SimpleIntegerProperty(maMucThu);
        this.maMonHoc = new SimpleIntegerProperty(maMonHoc);
        this.soTinChi = new SimpleIntegerProperty();
        this.giaoVienGiangDay = new SimpleStringProperty(giaoVienGiangDay);
        this.thoiGian = new SimpleStringProperty(thoiGian.toString());
    }

    private HocPhan(int ma, int maMonHoc, int soTinChi, int maMucThu, String giaoVienGiangDay, String thoiGian) {
        this.ma = new SimpleIntegerProperty(ma);
        this.maMucThu = new SimpleIntegerProperty(maMucThu);
        this.maMonHoc = new SimpleIntegerProperty(maMonHoc);
        this.soTinChi = new SimpleIntegerProperty(soTinChi);
        this.giaoVienGiangDay = new SimpleStringProperty(giaoVienGiangDay);
        this.thoiGian = new SimpleStringProperty(thoiGian.toString());
    }

    public static HocPhan getInstanceID(int ma, int maMonHoc, short soTinChi, int maMucThu, String giaoVienGiangDay, String thoiGian) {
        return new HocPhan(ma, maMonHoc, soTinChi, maMucThu, giaoVienGiangDay, thoiGian);
    }

    public int getMa() {
        return ma.getValue();
    }

    public int getMaMucThu() {
        return maMucThu.getValue();
    }

    public void setMaMucThu(int maMucThu) {
        this.maMucThu.setValue(maMucThu);
    }

    public int getMaMonHoc() {
        return maMonHoc.getValue();
    }

    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc.setValue(maMonHoc);
    }

    public Integer getSoTinChi() {
        return soTinChi.getValue();
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi.setValue(soTinChi);
    }

    public String getGiaoVienGiangDay() {
        return giaoVienGiangDay.getValue();
    }

    public void setGiaoVienGiangDay(String giaoVienGiangDay) {
        this.giaoVienGiangDay.setValue(giaoVienGiangDay);
    }

    public String getThoiGian() {
        return thoiGian.getValue();
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian.setValue(thoiGian.toString());
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


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<HocPhan> getAll() throws SQLException {
            return searchDB.getDsHocPhan();
        }
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
            return new HocPhan(id, hocPhan.getMaMonHoc(), hocPhan.getSoTinChi(), hocPhan.getMaMucThu(),
                    hocPhan.getGiaoVienGiangDay(), hocPhan.getThoiGian());
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

        public static Boolean whereId(String where) {
            return Delete.where("mahp = " + where);
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

        public static Boolean whereId(String where, HocPhan hp) throws SQLException {
            return Update.where("mahp = " + where, hp);
        }
    }
}
