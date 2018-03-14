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

    public DoiTuong(String ten) {
        this.ten = ten;
    }

    /**
     * Hàm này trả về ID nếu muốn tạo construct có ID, và tên
     * giống Factory
     *
     * @param id
     * @param ten
     * @return
     */
    public static DoiTuong getInstanceID(int id, String ten) {
        return new DoiTuong(id, ten);
    }

    private DoiTuong(int ma, String ten) {
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

    public static class Search {
        private Search() {
        }

        public static DoiTuong where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM DOITUONG WHERE " + where);
            resultSet.next();

            return searchDB.getDoiTuong(resultSet);
        }
    }

    /**
     * @return Lay tat ca sinh vien trong csdl
     * @throws SQLException
     */
    public static List<DoiTuong> getAll() throws SQLException {
        return searchDB.getDsDoiTuong();
    }

    public static DoiTuong Insert(DoiTuong doiTuong) throws SQLException {
        try {

            int id = InsertDB.getInstance().initInsert("DOITUONG");

            statement = "INSERT INTO DOITUONG(loaidt) VALUES" +
                    "(" +
//                    doiTuong.getMa() + ", " +
                    "N'" + doiTuong.getTen() + "' " +
                    ")";

//            System.out.println(id);
            // wait form input
            // wait form input
            // wait form input

//            DoiTuong.Update.where("madt = " + id, new DoiTuong(id, doiTuong.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return new DoiTuong(id, doiTuong.getTen());
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
         * @param where       DK - update
         * @param newDoiTuong DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, DoiTuong newDoiTuong) throws SQLException {
            try {
                statement = "UPDATE DOITUONG " +
                        "SET " +
                        "loaidt = N'" + newDoiTuong.getTen() + "' " +
                        "WHERE " + where;
                System.out.println(statement);
                UpdateDB.getInstance().updateCommand(statement);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
