package model;

import model.objects.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestModelHere {
    public int y = 10;

    public TestModelHere() {
        this.y = 11;
        final int x = y;
    }

    @Test
    void DoTest() throws SQLException, FileNotFoundException {


//        // chen doi tuong
//        DoiTuong dt = DoiTuong.Insert(new DoiTuong("Bui Minh Hoang"));
//        System.out.println(dt);
//        // tra ve ban ghi dau tien
//        System.out.println(DoiTuong.Search.where("madt = " + dt.getMa()));
//        // xoa doi tuong
//        System.out.println(DoiTuong.Delete.where("madt = " + dt.getMa()));


//        System.out.println(SinhVien.Delete.whereId("1"));
//        System.out.println(new Date(System.currentTimeMillis()).toString());
//    }
//}

//          BoMon.Insert(new BoMon(""))
//        System.out.println(object.getClass().getSimpleName());
//        System.out.println(new Date(System.currentTimeMillis()));
        String s = "270197";
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        try (BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\DEVON\\Documents\\GitHub\\ThucTapNhom_\\quanly_hocphisinhvien\\src\\giangduong")))) {
            try (BufferedReader data1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\DEVON\\Documents\\GitHub\\ThucTapNhom_\\quanly_hocphisinhvien\\src\\hp")))) {

                String readLine = "";
                ArrayList<String> arrayGD = new ArrayList<>();
                ArrayList<Integer> arrayHP = new ArrayList<>();
//                ArrayList<String> arrayMASV = new ArrayList<>();
                Random random = new Random();
                while ((readLine = data.readLine()) != null) {
                    arrayGD.add(readLine);
                }
                while ((readLine = data1.readLine()) != null) {
                    arrayHP.add(Integer.parseInt(readLine));
                }
//                    while ((readLine = data2.readLine()) != null) {
//                        arrayMASV.add(readLine);
//                    }
//            for (int i = 0; i < 500; i++) {
//                int maNganh = random.nextInt(arrayDataNganh.size());
//                System.out.println("INSERT INTO NGANH(tennganh, maMonHoc) values(N'" + "Here!!!" + "', " +
//                        arrayDataNganh.get(maNganh) + "" +
//                        ")"
//                );
//            }

                for (int i = 0; i < arrayGD.size() * 2; i++) {
//                int maNganh = random.nextInt(arrayDataHocPhan.size());
                        a = random.nextInt(10);
                        b = random.nextInt(10);
                        c = random.nextInt(10);
                        d = random.nextInt(10);
                    System.out.println("INSERT INTO DANGKY(madk, magd, mahp) " +
                                    "values(" +
                                        (s + a + b + c + d) + ", " +
                                    arrayGD.get(random.nextInt(arrayGD.size())) +
                                    ", " + arrayHP.get(random.nextInt(arrayHP.size())) +
//                                        ", " + arrayMASV.get(random.nextInt(arrayMASV.size())) +
//                        tenGD + "" +
                                    ")\ngo"
                    );
                }
////                    for (int i = 0; i < 1000; i++) {
//////                int maNganh = random.nextInt(arrayDataHocPhan.size());
////
////                        System.out.println("INSERT INTO DANGKY(magd, mahp, masv) " +
////                                        "values(" +
////                                        "" + arrayGD.get(random.nextInt(arrayGD.size())) +
////                                        ", " + arrayHP.get(random.nextInt(arrayHP.size())) +
////                                        ", " + arraySV.get(random.nextInt(arrayHP.size())) +
//////                        tenGD + "" +
////                                        ")"
////                        );
////                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
