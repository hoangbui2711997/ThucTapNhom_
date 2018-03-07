package model.database;

import model.core.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDB {

    public Connection conn = null;
    private List<HocPhan> dsHocPhan;
    private List<BoMon> dsBoMon;
    private List<DangKy> dsDangKy;
    private List<DoiTuong> dsDoiTuong;
    private List<GiangDuong> dsGiangDuong;
    private List<Khoa> dsKhoa;
    private List<MonHoc> dsMonHoc;
    private List<MucThu> dsMucTHu;
    private List<Nganh> dsNganh;
    private List<PhieuThu> dsPhieuThu;
    private List<SinhVien> dsSinhVien;
    private String statement = "";

    /**
     * constructor cua QueryDB
     * @see "set" connection between database and java
     */
    public QueryDB() {
        this.conn = DB_Connection.getConnection();
    }

    /**
     *
     * @return Connection with database
     */
    public Connection getConn() {
        return conn;
    }

    /**
     *
     * @param statement truyền vào câu lệnh qua biến statement và được execute thông qua prepareStatement()
     * @return Trả về tập kết quả sau khi truy vấn (ResultSet)
     * @throws SQLException
     */
    // prepareStatement thực thi câu lệnh statement và trả về kết quả
    public ResultSet prepareStatement(String statement) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * @see  "Truyền" vào {@link ResultSet} và lấy về bộ dữ liệu tiếp theo của {@link ResultSet}
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng SinhVien
     * @throws SQLException
     */
    // Lấy từng SinhVien qua ResultSet
    public SinhVien getSV(ResultSet resultSet) throws SQLException{
        try {
            Object masv = resultSet.getObject(1);
            Object madt = resultSet.getObject(2);
            Object mabm = resultSet.getObject(3);
            Object tensv = resultSet.getObject(4);
            Object gioitinh = resultSet.getObject(5);
            Object ngaysinh = resultSet.getObject(6);
            Object diachi = resultSet.getObject(7);

            return new SinhVien((Integer) masv, (Integer) madt, (Integer) mabm, (String) tensv,
                    (String) diachi, (Boolean) gioitinh, (Date) ngaysinh);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng DangKy
     * @throws SQLException
     */
    // Lấy từng DangKy qua ResultSet
    public DangKy getDK(ResultSet resultSet) throws SQLException{
        try {
            Object madk = resultSet.getObject(1);
            Object magd = resultSet.getObject(2);
            Object mahp = resultSet.getObject(3);
            Object masv = resultSet.getObject(4);
            Object thoigianDK = resultSet.getObject(5);

            return new DangKy((Integer) madk, (Integer) magd, (Integer) mahp, (Integer) masv,
                    (Date) thoigianDK);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng GiangDuong
     * @throws SQLException
     */
    // Lấy từng GiangDuong qua ResultSet
    public GiangDuong getGiangDuong(ResultSet resultSet) throws SQLException{
        try {
            Object magd = resultSet.getObject(1);
            Object tengd = resultSet.getObject(2);

            return new GiangDuong((Integer) magd, (String) tengd);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng HocPhan
     * @throws SQLException
     */
    // Lấy từng HocPhan qua ResultSet
    public HocPhan getHocPhan(ResultSet resultSet) throws SQLException{
        try {
            Object mahp = resultSet.getObject(1);
            Object mamonhoc = resultSet.getObject(2);
            Object tenhp = resultSet.getObject(3);
            Object sotinchi = resultSet.getObject(4);
            Object mamucthu = resultSet.getObject(5);
            //gvgd là giáo viên giảng dạy
            Object gvgd = resultSet.getObject(6);
            Object thoigian = resultSet.getObject(7);

            return new HocPhan((Integer) mahp, (Integer) mamonhoc, (Integer) tenhp, (byte) sotinchi,
                    (String) mamucthu, (String) gvgd, (Date) thoigian);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Khoa
     * @throws SQLException
     */
    // Lấy từng Khoa qua ResultSet
    public Khoa getKhoa(ResultSet resultSet) throws SQLException{
        try {
            Object makhoa = resultSet.getObject(1);
            Object tenKhoa = resultSet.getObject(2);

            return new Khoa((Integer) makhoa, (String) tenKhoa);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng MonHoc
     * @throws SQLException
     */
    // Lấy từng MonHoc qua ResultSet
    public MonHoc getMonHoc(ResultSet resultSet) throws SQLException{
        try {
            Object mamonhoc = resultSet.getObject(1);
            Object tenmonhoc = resultSet.getObject(2);

            return new MonHoc((Integer) mamonhoc, (String) tenmonhoc);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng MucThu
     * @throws SQLException
     */
    // Lấy từng MucThu qua ResultSet
    public MucThu getMucThu(ResultSet resultSet) throws SQLException{
        try {
            Object mamucthu = resultSet.getObject(1);
            Object mota = resultSet.getObject(2);
            Object sotien = resultSet.getObject(3);

            return new MucThu((Integer) mamucthu, (String) mota, (Long) sotien);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng Nganh
     * @throws SQLException
     */
    // Lấy từng Nganh qua ResultSet
    public Nganh getNganh(ResultSet resultSet) throws SQLException{
        try {
            Object manganh = resultSet.getObject(1);
            Object tennganh = resultSet.getObject(2);

            return new Nganh((Integer) manganh, (String) tennganh);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng PhieuThu
     * @throws SQLException
     */
    // Lấy từng PhieuThu qua ResultSet
    public PhieuThu getPhieuThu(ResultSet resultSet) throws SQLException{
        try {
            Object mapt = resultSet.getObject(1);
            Object sotien = resultSet.getObject(2);
            Object masv = resultSet.getObject(3);
            Object ngaybatdauthu = resultSet.getObject(4);
            Object ngaynop = resultSet.getObject(5);
            Object trangthai = resultSet.getObject(6);

            return new PhieuThu((Integer) mapt, (Integer) sotien, (Integer) masv, (Date) ngaybatdauthu,
                    (Date) ngaynop, (Boolean) trangthai);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng DoiTuong
     * @throws SQLException
     */
    // Lấy từng DoiTuong qua ResultSet
    public DoiTuong getDoiTuong(ResultSet resultSet) throws SQLException{
        try {
            Object madt = resultSet.getObject(1);
            Object tenDoiTuong = resultSet.getObject(2);
            Object tenhp = resultSet.getObject(3);
            Object sotinchi = resultSet.getObject(4);
            Object mamucthu = resultSet.getObject(5);
            //gvgd là giáo viên giảng dạy
            Object gvgd = resultSet.getObject(6);
            Object thoigian = resultSet.getObject(7);

            return new DoiTuong((Integer) madt, (String) tenDoiTuong);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

    /**
     *
     * @param resultSet
     * @return Trả về bộ dữ liệu hiện thời của resultset sau khi truy vấn bảng BoMon
     * @throws SQLException
     */
    // Lấy từng BoMon qua ResultSet
    public BoMon getBoMon(ResultSet resultSet) throws SQLException{
        try {
            Object mabm = resultSet.getObject(1);
            Object tenbm = resultSet.getObject(2);

            return new BoMon((Integer) mabm, (String) tenbm);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Nếu ResultSet không có gì thì trả về null
        return null;
    }

//    ----------------------------------------------------------------------------------------
//    ----------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------
//    ----------------------------------------------------------------------------------------
//    ----------------------------------------------------------------------------------------

    /**
     *
     * @return Trả về câu lệnh hiện thời dùng để truy vấn
     */
    public String getStatement() {
        return statement;
    }

    /**
     *
     * @param statement - đặt câu lệnh cho biến statement
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     *
     * @return trả về danh sách Học Phần
     * @throws SQLException
     */
    public List<HocPhan> getDsHocPhan() throws SQLException{
        List<HocPhan> dshp = new ArrayList();
        ResultSet dsHocPhan = prepareStatement("SELECT * FROM HOCPHAN");

        while(dsHocPhan.next()) {
            HocPhan hp = getHocPhan(dsHocPhan);
            dshp.add(hp);
        }

        this.dsHocPhan = dshp;
        return dshp;
    }

    public List<BoMon> getDsBoMon() throws SQLException{
        List<BoMon> dsbm = new ArrayList();
        ResultSet dsBoMon = prepareStatement("SELECT * FROM BOMON");

        while(dsBoMon.next()) {
            BoMon bm = getBoMon(dsBoMon);
            dsbm.add(bm);
        }

        this.dsBoMon = dsbm;
        return dsbm;
    }

    public List<DangKy> getDsDangKy() throws SQLException{
        List<DangKy> dsdk = new ArrayList();
        ResultSet dsDangKy = prepareStatement("SELECT * FROM DANGKY");

        while(dsDangKy.next()) {
            DangKy dk = getDK(dsDangKy);
            dsdk.add(dk);
        }

        this.dsDangKy = dsdk;
        return dsdk;
    }

    public List<DoiTuong> getDsDoiTuong() throws SQLException{
        List<DoiTuong> dsdt = new ArrayList();
        ResultSet dsDoiTuong = prepareStatement("SELECT * FROM DOITUONG");

        while(dsDoiTuong.next()) {
            DoiTuong dt = getDoiTuong(dsDoiTuong);
            dsdt.add(dt);
        }

        this.dsDoiTuong = dsdt;
        return dsdt;
    }

    public List<GiangDuong> getDsGiangDuong() throws SQLException{
        List<GiangDuong> dsgd = new ArrayList();
        ResultSet dsGiangDuong = prepareStatement("SELECT * FROM GIANGDUONG");

        while(dsGiangDuong.next()) {
            GiangDuong gd = getGiangDuong(dsGiangDuong);
            dsgd.add(gd);
        }

        this.dsGiangDuong = dsgd;
        return dsgd;
    }

    public List<Khoa> getDsKhoa() throws SQLException{
        List<Khoa> dsk = new ArrayList();
        ResultSet dsKhoa = prepareStatement("SELECT * FROM KHOA");

        while(dsKhoa.next()) {
            Khoa khoa = getKhoa(dsKhoa);
            dsk.add(khoa);
        }

        this.dsKhoa = dsk;
        return dsk;
    }

    public List<MonHoc> getDsMonHoc() throws SQLException {
        List<MonHoc> dsmh = new ArrayList();
        ResultSet dsMonHoc = prepareStatement("SELECT * FROM MONHOC");

        while (dsMonHoc.next()) {
            MonHoc mh = getMonHoc(dsMonHoc);
            dsmh.add(mh);
        }

        this.dsMonHoc = dsmh;
        return dsmh;
    }

    public List<MucThu> getDsMucTHu() throws SQLException{
        List<MucThu> dsmt = new ArrayList();
        ResultSet dsMucThu = prepareStatement("SELECT * FROM MUCTHU");

        while(dsMucThu.next()) {
            MucThu gd = getMucThu(dsMucThu);
            dsmt.add(gd);
        }

        this.dsMucTHu = dsmt;
        return dsmt;
    }

    public List<Nganh> getDsNganh() throws SQLException{
        List<Nganh> dsn = new ArrayList();
        ResultSet dsNganh = prepareStatement("SELECT * FROM NGANH");

        while(dsNganh.next()) {
            Nganh nganh = getNganh(dsNganh);
            dsn.add(nganh);
        }

        this.dsNganh = dsn;
        return dsn;
    }

    public List<PhieuThu> getDsPhieuThu() throws SQLException{
        List<PhieuThu> dspt = new ArrayList();
        ResultSet dsPhieuThu = prepareStatement("SELECT * FROM PHIEUTHU");

        while(dsPhieuThu.next()) {
            PhieuThu gd = getPhieuThu(dsPhieuThu);
            dspt.add(gd);
        }

        this.dsPhieuThu = dspt;
        return dspt;
    }

    public List<SinhVien> getDsSinhVien() throws SQLException{
        List<SinhVien> dssv = new ArrayList();
        ResultSet dsSinhVien = prepareStatement("SELECT * FROM SINHVIEN");

        while(dsSinhVien.next()) {
            SinhVien sv = getSV(dsSinhVien);
            dssv.add(sv);
        }

        this.dsSinhVien = dssv;
        return dssv;
    }
}
