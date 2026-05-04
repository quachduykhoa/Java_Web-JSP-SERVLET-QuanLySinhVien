package dao;

import context.DBContext;
import model.*;
import java.sql.*;
import java.util.*;

public class SinhVienDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// Lấy danh sách Trường cho ComboBox
	public List<Truong> getAllTruong() {
		List<Truong> list = new ArrayList<>();
		String sql = "SELECT MaTruong, TenTruong FROM TRUONG";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Truong(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Lấy danh sách Ngành cho ComboBox
	public List<Nganh> getAllNganh() {
		List<Nganh> list = new ArrayList<>();
		String sql = "SELECT MaNganh, TenNganh FROM NGANH";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Nganh(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//insertGraduation
	public void insertGraduation(String soCMND, String hoTen, String email, String soDT, String diaChi, String maTruong,
	        String maNganh, String heTN, String ngayTN, String loaiTN) throws Exception { 
	    
	    conn = new DBContext().getConnection();

	    // INSERT vào bảng SINHVIEN
	    String sqlSV = "INSERT INTO SINHVIEN (SoCMND, HoTen, Email, SoDT, DiaChi) VALUES (?, ?, ?, ?, ?)";
	    ps = conn.prepareStatement(sqlSV);
	    ps.setString(1, soCMND);
	    ps.setString(2, hoTen);
	    ps.setString(3, email);
	    ps.setString(4, soDT);
	    ps.setString(5, diaChi);
	    ps.executeUpdate();

	    // INSERT vào bảng TOT_NGHIEP
	    String sqlTN = "INSERT INTO TOT_NGHIEP (SoCMND, MaTruong, MaNganh, HeTN, NgayTN, LoaiTN) VALUES (?, ?, ?, ?, ?, ?)";
	    ps = conn.prepareStatement(sqlTN);
	    ps.setString(1, soCMND);
	    ps.setString(2, maTruong);
	    ps.setString(3, maNganh);
	    ps.setString(4, heTN);
	    ps.setString(5, ngayTN);
	    ps.setString(6, loaiTN);
	    ps.executeUpdate();
	    
	    if(ps != null) ps.close();
	    if(conn != null) conn.close();
	}

	// Tìm kiếm và hiển thị kết quả (JOIN 4 bảng)
	public List<Object[]> searchStudents(String keyword) {
		List<Object[]> list = new ArrayList<>();
		// Câu lệnh JOIN lấy đủ thông tin
		String sql = "SELECT sv.SoCMND, sv.HoTen, sv.Email, t.TenTruong, n.TenNganh, tn.NgayTN " + "FROM SINHVIEN sv "
				+ "JOIN TOT_NGHIEP tn ON sv.SoCMND = tn.SoCMND " + "JOIN TRUONG t ON tn.MaTruong = t.MaTruong "
				+ "JOIN NGANH n ON tn.MaNganh = n.MaNganh " + "WHERE sv.HoTen LIKE ? OR sv.SoCMND LIKE ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(sql);
			String searchVal = "%" + (keyword == null ? "" : keyword) + "%";
			ps.setString(1, searchVal);
			ps.setString(2, searchVal);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Object[] { rs.getString("SoCMND"), rs.getString("HoTen"), rs.getString("Email"),
						rs.getString("TenTruong"), rs.getString("TenNganh"), rs.getDate("NgayTN") });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}