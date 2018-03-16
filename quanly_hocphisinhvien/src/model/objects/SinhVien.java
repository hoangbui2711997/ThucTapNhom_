package model.objects;

import javafx.beans.property.SimpleBooleanProperty;
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

public class SinhVien {
    private List<SinhVien> dsSinhVien;
    private SimpleIntegerProperty maSV, maDT, maBoMon;
    private SimpleStringProperty tenSV, diaChi;
    private SimpleBooleanProperty gioiTinh;
    private SimpleStringProperty ngaySinh;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public SinhVien(int maDT, int maBoMon, String tenSV, String diaChi, boolean gioiTinh, String ngaySinh) {
        this.maDT = new SimpleIntegerProperty(maDT);
        this.maBoMon = new SimpleIntegerProperty(maBoMon);
        this.tenSV = new SimpleStringProperty(tenSV);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.gioiTinh = new SimpleBooleanProperty(gioiTinh);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
    }

    private SinhVien(int maSV, int maDT, int maBoMon, String tenSV, String diaChi, boolean gioiTinh, String ngaySinh) {
        this.maSV = new SimpleIntegerProperty(maSV);
        this.maDT = new SimpleIntegerProperty(maDT);
        this.maBoMon = new SimpleIntegerProperty(maBoMon);
        this.tenSV = new SimpleStringProperty(tenSV);
        this.diaChi = new SimpleStringProperty(diaChi);
        this.gioiTinh = new SimpleBooleanProperty(gioiTinh);
        this.ngaySinh = new SimpleStringProperty(ngaySinh);
    }

    public static SinhVien getInstanceID(int maSV, int maDT, int maBoMon, String tenSV, String diaChi, boolean gioiTinh, String ngaySinh) {
        return new SinhVien(maSV, maDT, maBoMon, tenSV, diaChi, gioiTinh, ngaySinh);
    }

    public List<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    public void setDsSinhVien(List<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }

    public int getMaSV() {
        return maSV.getValue();
    }

    public int getMaDT() {
        return maDT.getValue();
    }

    public void setMaDT(int maDT) {
        this.maDT.setValue(maDT);
    }

    public int getMaBoMon() {
        return maBoMon.getValue();
    }

    public void setMaBoMon(int maBoMon) {
        this.maBoMon.setValue(maBoMon);
    }

    public String getTenSV() {
        return tenSV.getValue();
    }

    public void setTenSV(String tenSV) {
        this.tenSV.setValue(tenSV);
    }

    public String getDiaChi() {
        return diaChi.getValue();
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.setValue(diaChi);
    }

    public boolean getGioiTinh() {
        return gioiTinh.getValue();
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh.setValue(gioiTinh);
    }

    public String getNgaySinh() {
        return ngaySinh.getValue();
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh.setValue(ngaySinh);
    }

    public static class Search {
        private Search() {
        }

        public static SinhVien where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM SINHVIEN WHERE " + where);
            resultSet.next();

            return searchDB.getSV(resultSet);
        }

        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<SinhVien> getAll() throws SQLException {
            return searchDB.getDsSinhVien();
        }
    }

    public static SinhVien Insert(SinhVien sinhVien) throws SQLException {
        try {
//            statement = "INSERT INTO SINHVIEN(masv, madt, mabm, tensv, gioitinh, ngaysinh, diachi) VALUES" +
//                    "(" +
//                    sinhVien.getMaSV() + ", " +
//                    sinhVien.getMaDT() + ", " +
//                    sinhVien.getMaBoMon() + ", " +
//                    "N'" + sinhVien.getTenSV() + "', " +
//                    "N'" + (sinhVien.getGioiTinh() == true ? "Nam" : "Nữ") + "', " +
//                    "'" + sinhVien.getNgaySinh() + "', " +
//                    "N'" + sinhVien.getDiaChi() + "', " +
//                    ")";
            int id = InsertDB.getInstance().initInsert("SINHVIEN");

//            SinhVien.Update.where("masv = " + id, new SinhVien(id, sinhVien.getMaDT(), sinhVien.getMaBoMon(),
//                    sinhVien.getTenSV(), sinhVien.getDiaChi(), sinhVien.getGioiTinh(), sinhVien.getNgaySinh()));
            InsertDB.getInstance().insertCommand(statement);
            return new SinhVien(id, sinhVien.getMaDT(), sinhVien.getMaBoMon(),
                    sinhVien.getTenSV(), sinhVien.getDiaChi(), sinhVien.getGioiTinh(), sinhVien.getNgaySinh());
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
                statement = "DELETE SINHVIEN WHERE " + where;
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
        return "SinhVien{" +
                "maSV=" + maSV +
                ", maDT=" + maDT +
                ", maBoMon=" + maBoMon +
                ", tenSV='" + tenSV + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", ngaySinh=" + ngaySinh +
                '}';
    }

    public static class Update {

        /**
         * @param where       DK - update
         * @param newSinhVien DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, SinhVien newSinhVien) throws SQLException {
            try {
                statement = "UPDATE SINHVIEN set " +
//                        "masv = " + newSinhVien.getMaSV() + ", " +
                        "madt = " + newSinhVien.getMaDT() + ", " +
                        "mabm = " + newSinhVien.getMaBoMon() + ", " +
                        "tensv = N'" + newSinhVien.getTenSV() + "', " +
                        "gioitinh = N'" + (newSinhVien.getGioiTinh() == true ? "Nam" : "Nữ") + "', " +
                        "ngaysinh = '" + newSinhVien.getNgaySinh() + "' " +
                        "diachi = N'" + newSinhVien.getDiaChi() + "' " +

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
