package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GiangDuong {
    private int ma;
    private String ten;
    private List<DangKy> dsDangKy = null;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public GiangDuong(int ma, String ten) {
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

    public List<DangKy> getDsDangKy() {
        return dsDangKy;
    }

    public void setDsDangKy(List<DangKy> dsDangKy) {
        this.dsDangKy = dsDangKy;
    }

    public static class Search{
        private Search() {}

        public synchronized static GiangDuong where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM GIANGDUONG WHERE " + where);
                resultSet.next();

                return searchDB.getGiangDuong(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<GiangDuong> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsGiangDuong();
            }
        }
    }

    public static Boolean Insert(GiangDuong giangDuong) throws SQLException {
        try {
            statement = "INSERT INTO GIANGDUONG VALUES" +
                    "(" +
                    giangDuong.getMa() + ", " +
                    "N'"+ giangDuong.getTen() + "'" +
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
                statement = "DELETE GIANGDUONG WHERE " + where;
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
         * @param newGiangDuong DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, GiangDuong newGiangDuong) throws SQLException {
            try {
                statement = "UPDATE GIANGDUONG " +
                        "SET magd = " + newGiangDuong.getMa() + ", " +
                        "tengd = N'" + newGiangDuong.getTen() + "' " +
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
