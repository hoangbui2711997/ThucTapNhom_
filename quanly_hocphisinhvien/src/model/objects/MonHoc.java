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

public class MonHoc {
    private SimpleIntegerProperty ma;
    private SimpleStringProperty ten;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public MonHoc(String ten) {
        this.ten = new SimpleStringProperty(ten);
    }

    private MonHoc(int ma, String ten) {
        this.ma = new SimpleIntegerProperty(ma);
        this.ten = new SimpleStringProperty(ten);
    }

    public static MonHoc getInstance(int ma, String ten) {
        return new MonHoc(ma, ten);
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
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

        public static MonHoc where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM MONHOC WHERE " + where);
            resultSet.next();

            return searchDB.getMonHoc(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<MonHoc> getAll() throws SQLException {
            return searchDB.getDsMonHoc();
        }
    }

    public static MonHoc Insert(MonHoc monHoc) throws SQLException {
        try {
//            statement = "INSERT INTO MONHOC(mamonhoc, tenmonhoc) VALUES" +
//                    "(" + monHoc.getMa() + ", " +
//                    "N'" + monHoc.getTen() +"'" +
//                    ")";

            int id = InsertDB.getInstance().initInsert("MONHOC");

            // wait form input
            // wait form input
            // wait form input

//            MonHoc.Update.where("mamonhoc = " + id, new MonHoc(id, monHoc.getTen()));
            InsertDB.getInstance().insertCommand(statement);
            return new MonHoc(id, monHoc.getTen());
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
                statement = "DELETE MONHOC WHERE " + where;
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
         * @param newMonHoc DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, MonHoc newMonHoc) throws SQLException {
            try {
                statement = "UPDATE MONHOC " +
                        "SET " +
//                        "mamonhoc = " + newMonHoc.getMa() + ", " +
                        "tenmonhoc = N'" + newMonHoc.getTen() + "' " +
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
