package model.constract;

import java.sql.SQLException;

public interface IInsert {
    Boolean insertCommand(String statement) throws SQLException;
    int initInsert(String object) throws SQLException;
}
