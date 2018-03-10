package model.objects;

import model.database.DeleteDB;
import model.database.InsertDB;
import model.database.SearchDB;
import model.database.UpdateDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoiTuong {
    private List<SinhVien> dsSV;
    private int ma;
    private String ten;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;
    private static Object lock = new Object();

    public DoiTuong(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public List<SinhVien> getDsSV() {

        return dsSV;
    }

    public void setDsSV(List<SinhVien> dsSV) {
        this.dsSV = dsSV;
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

    @Override
    public String toString() {
        return "DoiTuong{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
    }

    public static class Search{
        private Search() {}

        public synchronized static DoiTuong where(String where) throws SQLException {
            synchronized (lock) {
                ResultSet resultSet = searchDB.searchCommand("SELECT * FROM DOITUONG WHERE " + where);
                resultSet.next();

                return searchDB.getDoiTuong(resultSet);
            }
        }

        /**
         *
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public synchronized static List<DoiTuong> getAll() throws SQLException {
            synchronized (lock) {
                return searchDB.getDsDoiTuong();
            }
        }
    }

    public static Boolean Insert(DoiTuong doiTuong) throws SQLException {
        try {
            statement = "INSERT INTO DOITUONG VALUES" +
                    "(" +
                    doiTuong.getMa() + ", " +
                    "N'" + doiTuong.getTen() + "' " +
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
                statement = "DELETE DOITUONG WHERE " + where;
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
         * @param newDoiTuong DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, DoiTuong newDoiTuong) throws SQLException {
            try {
                statement = "UPDATE DOITUONG " +
                        "SET madt = " + newDoiTuong.getMa() + ", " +
                        "tendt = N'" + newDoiTuong.getTen() + "', " +
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
