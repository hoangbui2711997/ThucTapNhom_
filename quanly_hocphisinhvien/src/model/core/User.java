package model.core;

import model.database.QueryDB;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     *
     * @param nguoidung
     * @param password
     * @return Trả về true nếu người dùng tồn tại false nếu không tồn tại
     * @throws SQLException
     */
    public static boolean checkSignin(String nguoidung, String password) throws SQLException {
        QueryDB queryDB = new QueryDB();

        // CheckUser là function viết kiểm tra xem người dùng có tồn tại hay không
        // Trả về true hoặc false
        ResultSet resultSet = queryDB.prepareStatement("" +
                "select dbo.checkUser('"+ nguoidung +"', '"+ password +"')");
        resultSet.next();

        // Vì trả về true hoặc false nên ép kiểu boolean còn ko sẽ tự throw exception
        return (Boolean) resultSet.getObject(1);
    }
}
