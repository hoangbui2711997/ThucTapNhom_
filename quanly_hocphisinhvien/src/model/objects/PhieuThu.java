package model.objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuThu {
    private SimpleIntegerProperty ma, maSV;
    private SimpleLongProperty soTien;
    private SimpleStringProperty ngayBatDauThu, ngayNop;
    private SimpleBooleanProperty trangThai;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public PhieuThu(int maSV, long soTien, String ngayBatDauThu, String ngayNop, boolean trangThai) {
        this.maSV = new SimpleIntegerProperty(maSV);
        this.soTien = new SimpleLongProperty(soTien);
        this.ngayBatDauThu = new SimpleStringProperty(ngayBatDauThu.toString());
        this.ngayNop = ngayNop != null ? new SimpleStringProperty(ngayNop.toString()) : new SimpleStringProperty();
        this.trangThai = new SimpleBooleanProperty(trangThai);
    }

    private PhieuThu(int ma, int maSV, long soTien, String ngayBatDauThu, String ngayNop, boolean trangThai) {
        this.ma = new SimpleIntegerProperty(ma);
        this.maSV = new SimpleIntegerProperty(maSV);
        this.soTien = new SimpleLongProperty(soTien);
        this.ngayBatDauThu = new SimpleStringProperty(ngayBatDauThu.toString());
        this.ngayNop = ngayNop != null ? new SimpleStringProperty(ngayNop.toString()) : new SimpleStringProperty();
        this.trangThai = new SimpleBooleanProperty(trangThai);
    }

    public static PhieuThu getInstanceID(int ma, int maSV, long soTien, String ngayBatDauThu, String ngayNop, boolean trangThai) {
        return new PhieuThu(ma, maSV, soTien, ngayBatDauThu, ngayNop, trangThai);
    }

    public int getMa() {
        return ma.getValue();
    }

    public int getMaSV() {
        return maSV.getValue();
    }

    public void setMaSV(int maSV) {
        this.maSV.setValue(maSV);
    }

    public long getSoTien() {
        return soTien.getValue();
    }

    public void setSoTien(long soTien) {
        this.soTien.setValue(soTien);
    }

    public String getNgayBatDauThu() {
        return ngayBatDauThu.getValue();
    }

    public void setNgayBatDauThu(Date ngayBatDauThu) {
        this.ngayBatDauThu.setValue(ngayBatDauThu.toString());
    }

    public String getNgayNop() {
        return ngayNop.getValue();
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop.setValue(ngayNop.toString());
    }

    public boolean getTrangThai() {
        return trangThai.getValue();
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai.setValue(trangThai);
    }

    public static class Search {
        private Search() {
        }

        public static PhieuThu where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM PHIEUTHU WHERE " + where);
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

        public static List<PhieuThu> whereCondition(String where) throws SQLException{
            List<PhieuThu> listPhieuThu = new ArrayList<>();
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM PHIEUTHU WHERE " + where);

            while(resultSet.next()) {
                listPhieuThu.add(searchDB.getPhieuThu(resultSet));
            }

            return listPhieuThu;
        }
    }

    @Override
    public String toString() {
        return "PhieuThu{" +
                "ma=" + ma +
                ", maSV=" + maSV +
                ", soTien=" + soTien +
                ", ngayBatDauThu=" + ngayBatDauThu +
                ", ngayNop=" + ngayNop +
                ", trangThai=" + trangThai +
                '}';
    }

    public static PhieuThu Insert(PhieuThu phieuThu) throws SQLException {
        try {

            int id = InsertDB.getInstance().initInsert("PHIEUTHU");
            statement = "INSERT INTO PHIEUTHU VALUES" +
                    "(" +
//                    id + ", " +
                    phieuThu.getSoTien() + ", " +
                    phieuThu.getMaSV() + ", " +
                    "'" + phieuThu.getNgayBatDauThu() + "', " +
                    "'" + (phieuThu.getNgayNop() == null ? "" : phieuThu.getNgayNop()) + "', " +
                    (phieuThu.getTrangThai() == true ? "1" : "0") + " " +
                    ")";

            // wait form input
            // wait form input
            // wait form input

//            PhieuThu.Update.where("mapt = " + id, new PhieuThu(id, phieuThu.getMaSV(),
//                    phieuThu.getSoTien(), phieuThu.getNgayBatDauThu(), phieuThu.getNgayNop(), phieuThu.getTrangThai()));
            InsertDB.getInstance().insertCommand(statement);
            return new
                    PhieuThu(id, phieuThu.getMaSV(), phieuThu.getSoTien(), phieuThu.getNgayBatDauThu(),
                    phieuThu.getNgayNop() == null ? "" : phieuThu.getNgayNop(), phieuThu.getTrangThai());
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
                statement = "DELETE PHIEUTHU WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return Delete.where("mapt = " + where);
        }
    }

    public static class Update {

        /**
         * @param where       DK - update
         * @param newPhieuThu DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, PhieuThu newPhieuThu) throws SQLException {
            try {
                statement = "UPDATE PHIEUTHU " +
                        "SET " +
//                        "mapt = " + newPhieuThu.getMa() + ", " +
                        "sotien = " + newPhieuThu.getSoTien() + ", " +
                        "masv = N'" + newPhieuThu.getMaSV() + "', " +
                        "ngaybatdauthu = " + newPhieuThu.getNgayBatDauThu() + ", " +
                        "'" +  newPhieuThu.getNgayNop() == null ? "" : newPhieuThu.getNgayNop() + "', " +
                        "trangthai = N'" + newPhieuThu.getTrangThai() + "' " +
                        "where " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, PhieuThu pt) throws SQLException {
            return Update.where("mapt = " + where, pt);
        }
    }
}
