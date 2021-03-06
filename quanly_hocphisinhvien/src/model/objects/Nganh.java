package model.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Nganh {
    private SimpleIntegerProperty ma;
    private SimpleStringProperty ten;
    private List<BoMon> dsBoMon;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public Nganh(String ten) {
        this.ten = new SimpleStringProperty(ten);
    }

    private Nganh(int ma, String ten) {
        this.ma = new SimpleIntegerProperty(ma);
        this.ten = new SimpleStringProperty(ten);
    }

    public static Nganh getInstanceID(int ma, String ten) {
        return new Nganh(ma, ten);
    }

    public List<BoMon> getDsBoMon() {
        return dsBoMon;
    }

    public void setDsBoMon(List<BoMon> dsBoMon) {
        this.dsBoMon = dsBoMon;
    }

    public int getMa() {
        return ma.getValue();
    }

    public String getTen() {
        return ten.getValue();
    }

    public void setTen(String ten) {
        this.ten.setValue(ten);
    }

    public static class Search {
        private Search() {
        }

        public static Nganh where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM NGANH WHERE " + where);
            resultSet.next();

            return searchDB.getNganh(resultSet);
        }

        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<Nganh> getAll() throws SQLException {
            return searchDB.getDsNganh();
        }
    }

    @Override
    public String toString() {
        return "Nganh{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
    }

    public static Nganh Insert(Nganh nganh, int maKhoa) throws SQLException {
        try {
//            statement = "INSERT INTO NGANH(manganh, tennganh, khoa) VALUES" +
//                    "(" + nganh.getMa() + ", " +
//                    "N'" + nganh.getTen() + "', " +
//                    maKhoa + ", " +
//                    ")";
            int id = InsertDB.getInstance().initInsert("NGANH");
            // wait form input
            // wait form input
            // wait form input

//            Nganh.Update.where("manganh = " + id, new Nganh(id, nganh.getTen()), maKhoa);
            InsertDB.getInstance().insertCommand(statement);
            return new Nganh(id, nganh.getTen());
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
                statement = "DELETE NGANH WHERE " + where;
                DeleteDB.getInstance().deleteCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where) {
            return Delete.where("manganh = " + where);
        }
    }

    public static class Update {

        /**
         * @param where    DK - update
         * @param newNganh DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Nganh newNganh) throws SQLException {
            try {
                statement = "UPDATE NGANH " +
                        "SET " +
//                        "manganh = " + newNganh.getMa() + ", " +
                        "tennganh = N'" + newNganh.getTen() + "' " +
//                        "makhoa = " + maKhoa +
                        "where " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public static Boolean whereId(String where, Nganh nganh) throws SQLException {
            return Update.where("manganh = " + where, nganh);
        }
    }
}
