package model.constract;

import java.sql.SQLException;

public interface IInsert {
    Boolean insertCommand(String statement) throws SQLException;
}
