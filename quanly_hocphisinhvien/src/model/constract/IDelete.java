package model.constract;

import java.sql.SQLException;

public interface IDelete {
    Boolean deleteCommand(String statement) throws SQLException;
}
