package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Khoa {
    private int ma;
    private String ten;
    private List<Nganh> dsNganh;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public Khoa(int ma, String ten) {
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

    public List<Nganh> getDsNganh() {
        return dsNganh;
    }

    public void setDsNganh(List<Nganh> dsNganh) {
        this.dsNganh = dsNganh;
    }

    public static class Search{
        private Search() {}

        public synchronized static Khoa where(String where) throws SQLException {
            synchronized (searchDB) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM KHOA WHERE " + where);
                resultSet.next();

                return searchDB.getKhoa(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<Khoa> getAll() throws SQLException {
            synchronized (searchDB) {
                return searchDB.getDsKhoa();
            }
        }
    }

    public static Boolean Insert(DoiTuong khoa) throws SQLException {
        try {
            statement = "INSERT INTO KHOA VALUES" +
                    "(" +
                    khoa.getMa() + ", " +
                    "N'" + khoa.getTen() + "' " +
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
                statement = "DELETE KHOA WHERE " + where;
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
         * @param newKhoa DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Khoa newKhoa) throws SQLException {
            try {
                statement = "UPDATE KHOA " +
                        "SET makhoa = " + newKhoa.getMa() + ", " +
                        "tenkhoa = N'" + newKhoa.getTen() + "', " +
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
