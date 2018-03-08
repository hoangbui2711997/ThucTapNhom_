package model.database;

import model.constract.IUpdate;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDB implements IUpdate{

    private Connection conn = null;
    private static UpdateDB instance = null;
    private static Object lock = new Object();

    private UpdateDB()
    {
        conn = DB_Connection.getConnection();
    }

    @Override
    public synchronized Boolean updateCommand(String statement) throws SQLException{
        try {
            synchronized (conn) {
                conn.prepareStatement(statement).executeQuery();
                return true;
            }
        } catch(SQLException e) {
            return false;
        }
    }

    public synchronized static UpdateDB getInstance() {
        synchronized (lock) {
            if(instance == null) {
                return new UpdateDB();
            }
        }
        return instance;
    }
}
