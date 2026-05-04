<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Khai báo tốt nghiệp</title>
</head>
<body>
    <form action="student" method="post">
        <h3>Khai báo thông tin tốt nghiệp</h3>
        
        <%-- Thông báo lỗi nếu có (Yêu cầu 2.3) --%>
        <% if(request.getAttribute("msg") != null) { %>
            <p style="color:red;"><%= request.getAttribute("msg") %></p>
        <% } %>

        <%-- Các trường thuộc bảng SINHVIEN --%>
        - Số CMND (*): <input type="text" name="soCMND"><br>
        - Họ tên: <input type="text" name="hoTen"><br>
        - Email: <input type="email" name="email"><br>
        - Số điện thoại: <input type="text" name="soDT"><br>
        - Địa chỉ: <input type="text" name="diaChi"><br>
        
        <hr>
        
        <%-- Các trường thuộc bảng TOT_NGHIEP --%>
        - Trường (*): 
        <select name="maTruong">
            <option value="">-- Chọn trường --</option>
            <% List<Truong> lt = (List<Truong>)request.getAttribute("listTruong");
               if(lt != null) for(Truong t : lt) { %>
                <option value="<%= t.getMaTruong() %>"><%= t.getTenTruong() %></option>
            <% } %>
        </select><br>
        
        - Ngành (*): 
        <select name="maNganh">
            <option value="">-- Chọn ngành --</option>
            <% List<Nganh> ln = (List<Nganh>)request.getAttribute("listNganh");
               if(ln != null) for(Nganh n : ln) { %>
                <option value="<%= n.getMaNganh() %>"><%= n.getTenNganh() %></option>
            <% } %>
        </select><br>
        
        - Hệ tốt nghiệp: 
        <select name="heTN">
            <option value="Đại học">Đại học</option>
            <option value="Cao đẳng">Cao đẳng</option>
            <option value="Liên thông">Liên thông</option>
        </select><br>
        
        - Ngày tốt nghiệp (*): <input type="date" name="ngayTN"><br>
        
        - Loại tốt nghiệp: 
        <select name="loaiTN">
            <option value="Xuất sắc">Xuất sắc</option>
            <option value="Giỏi">Giỏi</option>
            <option value="Khá">Khá</option>
            <option value="Trung bình">Trung bình</option>
        </select><br>
        
        <input type="submit" value="Lưu dữ liệu">
    </form>
    <a href="student?action=search">
        <button type="button">Xem danh sách tốt nghiệp</button>
    </a>
</body>
</html>