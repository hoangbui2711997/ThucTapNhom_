package model.constract;

import java.sql.SQLException;

public interface IUpdate {
    Boolean updateCommand(String statement) throws SQLException;
}
