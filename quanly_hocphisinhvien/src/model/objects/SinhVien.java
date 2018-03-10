package model.objects;

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
    private int maSV, maDT, maBoMon;
    private String tenSV, diaChi;
    private boolean gioiTinh;
    private Date ngaySinh;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public SinhVien(int maSV, int maDT, int maBoMon, String tenSV, String diaChi, boolean gioiTinh, Date ngaySinh) {
        this.maSV = maSV;
        this.maDT = maDT;
        this.maBoMon = maBoMon;
        this.tenSV = tenSV;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public List<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    public void setDsSinhVien(List<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public int getMaDT() {
        return maDT;
    }

    public void setMaDT(int maDT) {
        this.maDT = maDT;
    }

    public int getMaBoMon() {
        return maBoMon;
    }

    public void setMaBoMon(int maBoMon) {
        this.maBoMon = maBoMon;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public static class Search{
        private Search() {}

        public synchronized static SinhVien where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM SINHVIEN WHERE " + where);
                resultSet.next();

                return searchDB.getSV(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<SinhVien> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsSinhVien();
            }
        }
    }

    public static Boolean Insert(SinhVien sinhVien) throws SQLException {
        try {
            statement = "INSERT INTO SINHVIEN VALUES" +
                    "(" +
                    sinhVien.getMaSV() + ", " +
                    sinhVien.getMaDT() + ", " +
                    sinhVien.getMaBoMon() + ", " +
                    "N'" + sinhVien.getTenSV() + "', " +
                    "N'" + (sinhVien.getGioiTinh() == true ? "Nam" : "Nữ") + "', " +
                    "'" + sinhVien.getNgaySinh() + "', " +
                    "N'" + sinhVien.getDiaChi() + "', " +
                    ")";

            InsertDB.getInstance().insertCommand(statement);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static class Delete {

        /**
         *
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

    public static class Update {

        /**
         *
         * @param where DK - update
         * @param newSinhVien DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, SinhVien newSinhVien) throws SQLException {
            try {
                statement = "UPDATE SINHVIEN set " +
                        "masv = " + newSinhVien.getMaSV() + ", " +
                        "madt = " + newSinhVien.getMaDT() + ", " +
                        "mabm = " + newSinhVien.getMaBoMon() + ", " +
                        "tensv = N'" + newSinhVien.getTenSV() + "', " +
                        "gioitinh = N'" + (newSinhVien.getGioiTinh() == true ? "Nam" : "Nữ") + "', " +
                        "ngaysinh = '"+ newSinhVien.getNgaySinh() +"' " +
                        "diachi = N'"+ newSinhVien.getDiaChi() +"' " +

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
