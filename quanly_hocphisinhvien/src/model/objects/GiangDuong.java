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

    private GiangDuong(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public GiangDuong(String ten) {
        this.ten = ten;
    }

    public static GiangDuong getInstanceID(int ma, String ten) {
        return new GiangDuong(ma, ten);
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

    public List<DangKy> getDsDangKy() {
        return dsDangKy;
    }

    public void setDsDangKy(List<DangKy> dsDangKy) {
        this.dsDangKy = dsDangKy;
    }

    public static class Search {
        private Search() {
        }

        public static GiangDuong where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM GIANGDUONG WHERE " + where);
            resultSet.next();

            return searchDB.getGiangDuong(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<GiangDuong> getAll() throws SQLException {
            return searchDB.getDsGiangDuong();
        }
    }

    public static GiangDuong Insert(GiangDuong giangDuong) throws SQLException {
        try {


            int id = InsertDB.getInstance().initInsert("GIANGDUONG");

            statement = "INSERT INTO GIANGDUONG(tengd) VALUES" +
                    "(" +
//                    giangDuong.getMa() + ", " +
                    "N'"+ giangDuong.getTen() + "'" +
                    ")";


            // wait form input
            // wait form input
            // wait form input

//            GiangDuong.Update.where("magd = " + id, new GiangDuong(id, giangDuong.getTen()));

            InsertDB.getInstance().insertCommand(statement);
            return new GiangDuong(id, giangDuong.getTen());
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
                statement = "DELETE GIANGDUONG WHERE " + where;
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
        return "GiangDuong{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
    }

    public static class Update {

        /**
         * @param where         DK - update
         * @param newGiangDuong DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, GiangDuong newGiangDuong) throws SQLException {
            try {
                statement = "UPDATE GIANGDUONG " +
                        "SET " +
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
