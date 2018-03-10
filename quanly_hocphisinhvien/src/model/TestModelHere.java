package model;

import model.objects.BoMon;
import model.objects.DoiTuong;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestModelHere {
    @Test
    void DoTest() throws SQLException {
//        DoiTuong dt1 = new DoiTuong(1234, "Ten Cua doi tuong");
        ArrayList<DoiTuong> dt = (ArrayList<DoiTuong>) DoiTuong.Search.getAll();
        for(DoiTuong d : dt) {
            System.out.println(d.toString());
        }
    }
}
