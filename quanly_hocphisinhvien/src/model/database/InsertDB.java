package model.database;

import model.constract.IInsert;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertDB implements IInsert{

    private Connection conn;
    private static InsertDB instance = null;
    private InsertDB() {
        conn = DB_Connection.getConnection();
    }
    private static Object lock = new Object();

    public synchronized static InsertDB getInstance() {
        synchronized (lock) {
            if(instance == null) {
                return new InsertDB();
            }
        }
        return instance;
    }

    @Override
    public synchronized Boolean insertCommand(String statement) throws SQLException{
        try {
            synchronized (conn) {
                conn.prepareStatement(statement).executeQuery();
                return true;
            }
        } catch(SQLException e) {
            return false;
        }
    }


}
