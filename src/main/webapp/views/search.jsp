<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm</title>
</head>
<body>
    <form action="student" method="get">
        - Tìm kiếm sinh viên: 
        <input type="text" name="txtSearch" placeholder="Nhập tên hoặc CMND..." value="<%= request.getParameter("txtSearch") != null ? request.getParameter("txtSearch") : "" %>">
        <input type="submit" value="Tìm">
    </form>

    <br>
    <table border="1" style="width: 100%; border-collapse: collapse;">
        <tr style="background-color: #f2f2f2;">
            <th>Số CMND</th>
            <th>Họ Tên</th>
            <th>Email</th>
            <th>Trường</th>
            <th>Ngành</th>
            <th>Ngày TN</th>
        </tr>
        
        <% 
            List<Object[]> ls = (List<Object[]>)request.getAttribute("listS");
            if (ls != null && !ls.isEmpty()) {
                for (Object[] o : ls) { 
        %>
            <tr>
                <td>- <%= o[0] %></td>
                <td>- <%= o[1] %></td>
                <td>- <%= o[2] %></td> <%-- Email --%>
                <td>- <%= o[3] %></td> <%-- Tên Trường --%>
                <td>- <%= o[4] %></td> <%-- Tên Ngành --%>
                <td>- <%= o[5] %></td> <%-- Ngày TN --%>
            </tr>
        <% 
                } 
            } else if (request.getParameter("txtSearch") != null) { 
        %>
            <tr>
                <td colspan="6" style="text-align: center;">Không tìm thấy sinh viên nào phù hợp.</td>
            </tr>
        <% } %>
    </table>
    
    <br>
    <a href="student?action=loadAddForm">Quay lại trang đăng ký</a>
</body>
</html>