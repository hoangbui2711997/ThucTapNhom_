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

public class Khoa {
    private SimpleIntegerProperty ma;
    private SimpleStringProperty ten;
    private List<Nganh> dsNganh;
    private static SearchDB searchDB = SearchDB.getQueryDB();
    private static String statement;

    public Khoa(String ten) {
        this.ten = new SimpleStringProperty(ten);
    }

    private Khoa(int ma, String ten) {
        this.ma = new SimpleIntegerProperty(ma);
        this.ten = new SimpleStringProperty(ten);
    }

    public static Khoa getInstanceID(int ma, String ten) {
        return new Khoa(ma, ten);
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

    public List<Nganh> getDsNganh() {
        return dsNganh;
    }

    public void setDsNganh(List<Nganh> dsNganh) {
        this.dsNganh = dsNganh;
    }

    @Override
    public String toString() {
        return "Khoa{" +
                "ma=" + ma +
                ", ten='" + ten + '\'' +
                '}';
    }

    public static class Search {
        private Search() {
        }

        public static Khoa where(String where) throws SQLException {
            ResultSet resultSet = searchDB.searchCommand("SELECT * FROM KHOA WHERE " + where);
            resultSet.next();

            return searchDB.getKhoa(resultSet);
        }


        /**
         * @return Lay tat ca sinh vien trong csdl
         * @throws SQLException
         */
        public static List<Khoa> getAll() throws SQLException {
            return searchDB.getDsKhoa();
        }
    }

    public static Khoa Insert(Khoa khoa) throws SQLException {
        try {
//            statement = "INSERT INTO KHOA(makhoa, tenkhoa) VALUES" +
//                    "(" +
//                    khoa.getMa() + ", " +
//                    "N'" + khoa.getTen() + "' " +
//                    ")";
            int id = InsertDB.getInstance().initInsert("KHOA");

            // wait form input
            // wait form input
            // wait form input

//            Khoa.Update.where("makhoa = " + id, new Khoa(id, khoa.getTen()));
            InsertDB.getInstance().insertCommand(statement);
            return new Khoa(id, khoa.getTen());
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
         * @param where   DK - update
         * @param newKhoa DangKy update
         * @return
         * @throws SQLException
         */
        public static Boolean where(String where, Khoa newKhoa) throws SQLException {
            try {
                statement = "UPDATE KHOA " +
                        "SET " +
//                        "makhoa = " + newKhoa.getMa() + ", " +
                        "tenkhoa = N'" + newKhoa.getTen() + "' " +
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
