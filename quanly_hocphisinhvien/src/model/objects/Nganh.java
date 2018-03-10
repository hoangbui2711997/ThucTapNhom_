package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Nganh {
    private int ma;
    private String ten;
    private List<BoMon> dsBoMon;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public Nganh(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<BoMon> getDsBoMon() {
        return dsBoMon;
    }

    public void setDsBoMon(List<BoMon> dsBoMon) {
        this.dsBoMon = dsBoMon;
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

        public synchronized static Nganh where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM NGANH WHERE " + where);
                resultSet.next();

                return searchDB.getNganh(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<Nganh> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsNganh();
            }
        }
    }

    public static Boolean Insert(Nganh nganh, int maKhoa) throws SQLException {
        try {
            statement = "INSERT INTO NGANH VALUES" +
                    "(" + nganh.getMa() + ", " +
                    "N'" + nganh.getTen() + "', " +
                    maKhoa + ", " +
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
                statement = "DELETE NGANH WHERE " + where;
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
         * @param newNganh DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Nganh newNganh, int maKhoa) throws SQLException {
            try {
                statement = "UPDATE NGANH " +
                        "SET manganh = " + newNganh.getMa() + ", " +
                        "tennganh = N'" + newNganh.getTen() + "', " +
                        "makhoa = " + maKhoa + " " +
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
