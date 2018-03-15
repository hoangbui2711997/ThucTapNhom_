package model.database;

import model.constract.IInsert;
import model.objects.BoMon;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertDB implements IInsert{

    private Connection conn;
    private static InsertDB instance = null;
    private InsertDB() {
        conn = DB_Connection.getConnection();
    }
    private static Object lock = new Object();

    public static InsertDB getInstance() {
        synchronized (lock) {
            if(instance == null) {
                return new InsertDB();
            }
        }
        return instance;
    }

    @Override
    public Boolean insertCommand(String statement) throws SQLException{
        try {
            synchronized (conn) {
                conn.prepareStatement(statement).executeQuery();
                return true;
            }
        } catch(SQLException e) {
            return false;
        }
    }

    public int initInsert(String object) throws SQLException {
//        String statement = "";
//        switch (object) {
//            case "BOMON":
//                statement = "INSERT INTO BOMON(ten) VALUES('')";
//                break;
//            case "DANGKY":
//                statement = "INSERT INTO DANGKY(madk, magd, mahp, masv) VALUES(null, null, null, null)";
//                break;
//            case "DOITUONG":
//                statement = "INSERT INTO DOITUONG(loaidt) VALUES('')";
//                break;
//            case "GIANGDUONG":
//                statement = "INSERT INTO GIANGDUONG(tengd) VALUES('')";
//                break;
//            case "HOCPHAN":
//                statement = "INSERT INTO HOCPHAN(mamonhoc) VALUES(null)";
//                break;
//            case "KHOA":
//                statement = "INSERT INTO KHOA(tenkhoa) VALUES('')";
//                break;
//            case "MONHOC":
//                statement = "INSERT INTO MONHOC(tenmonhoc) VALUES('')";
//                break;
//            case "MUCTHU":
//                statement = "INSERT INTO MUCTHU(mota) VALUES('')";
//                break;
//            case "NGANH":
//                statement = "INSERT INTO NGANH(tennganh) VALUES('')";
//                break;
//            case "PHIEUTHU":
//                statement = "INSERT INTO PHIEUTHU(masv) VALUES(null)";
//                break;
//            case "SINHVIEN":
//                statement = "INSERT INTO PHIEUTHU(tensv) VALUES('')";
//                break;
//            default:
//                statement = "";
//                break;
//        }
        try {
//            if(statement.length() == 0) {
//                return -1;
//            } else {
//                InsertDB.getInstance().insertCommand(statement);
                return SearchDB.getQueryDB().getLastID(object);
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
