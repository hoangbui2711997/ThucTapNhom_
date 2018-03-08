package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhieuThu {
    private int ma, maSV;
    private long soTien;
    private Date ngayBatDauThu, ngayNop;
    private boolean trangThai;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public PhieuThu(int ma, int maSV, long soTien, Date ngayBatDauThu, Date ngayNop, boolean trangThai) {
        this.ma = ma;
        this.maSV = maSV;
        this.soTien = soTien;
        this.ngayBatDauThu = ngayBatDauThu;
        this.ngayNop = ngayNop;
        this.trangThai = trangThai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public Date getNgayBatDauThu() {
        return ngayBatDauThu;
    }

    public void setNgayBatDauThu(Date ngayBatDauThu) {
        this.ngayBatDauThu = ngayBatDauThu;
    }

    public Date getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public static class Search{
        private Search() {}

        public synchronized static PhieuThu where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM PHIEUTHU WHERE " + where);
                resultSet.next();

                return searchDB.getPhieuThu(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<PhieuThu> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsPhieuThu();
            }
        }
    }

    public static Boolean Insert(PhieuThu phieuThu) throws SQLException {
        try {
            statement = "INSERT INTO PHIEUTHU VALUES" +
                    "(" +
                    phieuThu.getMa() + ", " +
                    phieuThu.getSoTien() + ", " +
                    phieuThu.getMaSV() + ", " +
                    "'" + phieuThu.getNgayBatDauThu() + "', " +
                    "'" + phieuThu.getNgayNop() + "', " +
                    (phieuThu.getTrangThai() == true ? "1" : "0") + ", " +
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
                statement = "DELETE PHIEUTHU WHERE " + where;
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
         * @param newPhieuThu DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, PhieuThu newPhieuThu) throws SQLException {
            try {
                statement = "UPDATE PHIEUTHU set " +
                        "mahp = " + newPhieuThu.getMa() + ", " +
                        "sotien = " + newPhieuThu.getSoTien() + ", " +
                        "masv = N'" + newPhieuThu.getMaSV() + "', " +
                        "ngaybatdauthu = " + newPhieuThu.getNgayBatDauThu() + ", " +
                        "ngaynop = " + newPhieuThu.getNgayNop() + ", " +
                        "trangthai = N'"+ newPhieuThu.getTrangThai() +"' " +
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
