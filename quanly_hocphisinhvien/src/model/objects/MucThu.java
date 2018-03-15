package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MucThu {

    private int maMucThu;
    private String moTa;
    private double soTien;
    private List<HocPhan> dsHocPhan;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public MucThu(String moTa, long soTien) {
        this.moTa = moTa;
        this.soTien = soTien;
    }

    private MucThu(int maMucThu, String moTa, double soTien) {
        this.maMucThu = maMucThu;
        this.moTa = moTa;
        this.soTien = soTien;
    }

    public static MucThu getInstanceID(int maMucThu, String moTa, double soTien) {
        return new MucThu(maMucThu, moTa, soTien);
    }

    public int getMaMucThu() {
        return maMucThu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public List<HocPhan> getDsHocPhan() {
        return dsHocPhan;
    }

    public void setDsHocPhan(List<HocPhan> dsHocPhan) {
        this.dsHocPhan = dsHocPhan;
    }

    @Override
    public String toString() {
        return "MucThu{" +
                "maMucThu=" + maMucThu +
                ", moTa='" + moTa + '\'' +
                ", soTien=" + soTien +
                '}';
    }

    public static class Search {
        private Search() {
        }

        public static MucThu where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM MUCTHU WHERE " + where);
            resultSet.next();

            return searchDB.getMucThu(resultSet);
        }

        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<MucThu> getAll() throws SQLException {
            return searchDB.getDsMucTHu();
        }
    }

    public static MucThu Insert(MucThu mucThu) throws SQLException {
        try {
//            statement = "INSERT INTO MUCTHU(mamucthu, mota, sotien) VALUES" +
//                    "(" + mucThu.getMaMucThu() + ", " +
//                    "N'" + mucThu.getMoTa() + "', " +
//                    mucThu.getSoTien() +
//                    ")";
            int id = InsertDB.getInstance().initInsert("MUCTHU");

            MucThu.Update.where("mamucthu = " + id, new MucThu(id, mucThu.getMoTa(), mucThu.getSoTien()));
            InsertDB.getInstance().insertCommand(statement);
            return new MucThu(id, mucThu.getMoTa(), mucThu.getSoTien());
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
                statement = "DELETE MUCTHU WHERE " + where;
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
         * @param where     DK - update
         * @param newMucThu DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, MucThu newMucThu) throws SQLException {
            try {
                statement = "UPDATE MUCTHU " +
                        "SET " +
//                        "mamucthu = " + newMucThu.getMaMucThu() + ", " +
                        "mota = N'" + newMucThu.getMoTa() + "', " +
                        "sotien = " + newMucThu.getSoTien() + " " +
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
