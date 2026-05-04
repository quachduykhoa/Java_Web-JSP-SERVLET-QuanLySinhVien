package controller;

import dao.SinhVienDAO;
import model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class SinhVienController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        SinhVienDAO dao = new SinhVienDAO();

        if ("loadAddForm".equals(action)) {
            request.setAttribute("listTruong", dao.getAllTruong());
            request.setAttribute("listNganh", dao.getAllNganh());
            request.getRequestDispatcher("views/add-sinhvien.jsp").forward(request, response);
        } else {
            // Mặc định là tìm kiếm
            String txt = request.getParameter("txtSearch");
            request.setAttribute("listS", dao.searchStudents(txt != null ? txt : ""));
            request.getRequestDispatcher("views/search.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SinhVienDAO dao = new SinhVienDAO();

        String soCMND = request.getParameter("soCMND");
        String maTruong = request.getParameter("maTruong");
        String maNganh = request.getParameter("maNganh");
        String ngayTN = request.getParameter("ngayTN");

        //Validation
        if (soCMND == null || soCMND.isEmpty() || maTruong == null || maTruong.isEmpty() 
            || maNganh == null || maNganh.isEmpty() || ngayTN == null || ngayTN.isEmpty()) {
            
            request.setAttribute("msg", "Vui lòng nhập đầy đủ các trường bắt buộc!");
            request.setAttribute("listTruong", dao.getAllTruong());
            request.setAttribute("listNganh", dao.getAllNganh());
            request.getRequestDispatcher("views/add-sinhvien.jsp").forward(request, response);
            return; 
        }

        try {
            dao.insertGraduation(soCMND, request.getParameter("hoTen"), request.getParameter("email"), 
                                 request.getParameter("soDT"), request.getParameter("diaChi"), 
                                 maTruong, maNganh, request.getParameter("heTN"), ngayTN, request.getParameter("loaiTN"));
            
            response.sendRedirect("student?action=search");
            
        } catch (Exception e) {
            request.setAttribute("msg", "Lỗi: Số CMND này đã tồn tại trong hệ thống!");
            request.setAttribute("listTruong", dao.getAllTruong());
            request.setAttribute("listNganh", dao.getAllNganh());
            
            request.getRequestDispatcher("views/add-sinhvien.jsp").forward(request, response);
        }
    }
}