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
     * @param ten String
     */
    public BoMon(String ten) {
        this.ten = ten;
    }

    public static BoMon getInstanceID(int id, String ten) {
        return new BoMon(id, ten);
    }

    private BoMon(int ma, String ten) {
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
    public static class Search {
        private Search() {
        }

        public static BoMon where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM BOMON WHERE " + where);
            resultSet.next();

            return searchDB.getBoMon(resultSet);
        }
    }

    /**
     * @return Lay tat ca sinh vien trong csdl
     * @throws SQLException
     */
    public static List<BoMon> getAll() throws SQLException {
        return searchDB.getDsBoMon();
    }

    public static BoMon Insert(BoMon boMon) throws SQLException {
        try {



            int id = InsertDB.getInstance().initInsert("BOMON");

            statement = "INSERT BOMON(ten) VALUES" +
                    "(" +
//                    + boMon.getMa() + ", " +
                    "N'" + boMon.getTen() + "'" +
                    ")";
            // wait form input
            // wait form input
            // wait form input

//            BoMon.Update.where("mabm = " + id, new BoMon(id, boMon.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return new BoMon(id, boMon.getTen());
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
                statement = "DELETE BOMON WHERE " + where;
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
        return "BoMon{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
    }

    public static class Update {

        /**
         * @param newBoMon
         * @return
         * @throws SQLException
         */

        public static Boolean where(String where, BoMon newBoMon) throws SQLException {
            try {
                statement = "UPDATE BOMON " +
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
