package model.database;

import model.constract.IDelete;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteDB implements IDelete{

    private Connection conn;
    private static DeleteDB instance = null;
    private DeleteDB() {
        conn = DB_Connection.getConnection();
    }
    private static Object lock = new Object();

    public synchronized static DeleteDB getInstance() {
        synchronized (lock) {
            if(instance == null) {
                return new DeleteDB();
            }
        }
        return instance;
    }

    @Override
    public synchronized Boolean deleteCommand(String statement) throws SQLException{
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
