package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MonHoc {
    private int ma;
    private String ten;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public MonHoc(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public static class Search{
        private Search() {}

        public synchronized static MonHoc where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM MONHOC WHERE " + where);
                resultSet.next();

                return searchDB.getMonHoc(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<MonHoc> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsMonHoc();
            }
        }
    }

    public static Boolean Insert(MonHoc monHoc) throws SQLException {
        try {
            statement = "INSERT INTO MONHOC VALUES" +
                    "(" + monHoc.getMa() + ", " +
                    "N'" + monHoc.getTen() +"'" +
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
         *
         * @param where DK - update
         * @param newMonHoc DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, MonHoc newMonHoc) throws SQLException {
            try {
                statement = "UPDATE MONHOC " +
                        "SET mamonhoc = " + newMonHoc.getMa() + ", " +
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
