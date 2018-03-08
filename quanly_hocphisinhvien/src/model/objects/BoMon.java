package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoMon {

    private int ma;
    private String ten;
    private List<SinhVien> dsSV = null;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public List<SinhVien> getDsSV() {
        return dsSV;
    }

    public void setDsSV(List<SinhVien> dsSV) {
        this.dsSV = dsSV;
    }

    /**
     *
     * @param ma int
     * @param ten String
     */
    public BoMon(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * static Search class chiu trach nhiem tim kiem "tim kiem"
     */
    public static class Search{
        private Search() {}

        public synchronized static BoMon where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM BOMON WHERE " + where);
                resultSet.next();

                return searchDB.getBoMon(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<BoMon> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsBoMon();
            }
        }
    }

    public static Boolean Insert(BoMon boMon) throws SQLException {
        try {
            statement = "INSERT INTO BOMON VALUES" +
                    "("
                    + boMon.getMa() + ", " +
                    "N'" + boMon.getTen() + "'" +
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
                statement = "DELETE BOMON WHERE " + where;
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
         * @param newBoMon
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, BoMon newBoMon) throws SQLException {
            try {
                statement = "UPDATE BOMON " +
                        "SET mabm = " + newBoMon.getMa() + ", " +
                        "tenbm = N'" + newBoMon.getTen() + "' " +
                        "WHERE " + where;
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
