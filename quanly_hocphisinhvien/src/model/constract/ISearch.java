package model.constract;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISearch {
    public ResultSet searchCommand(String statement) throws SQLException;
}
