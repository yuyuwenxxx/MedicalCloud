package com.medical.servlet;

import com.medical.dao.UserDAO;
import com.medical.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User currentUser = (User) session.getAttribute("user");
        
        try {
            // 获取表单数据
            String realName = request.getParameter("realName");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String birthDateStr = request.getParameter("birthDate");
            
            // 更新用户信息
            currentUser.setRealName(realName);
            currentUser.setGender(gender);
            currentUser.setPhone(phone);
            currentUser.setEmail(email);
            currentUser.setAddress(address);
            
            // 处理出生日期
            if (birthDateStr != null && !birthDateStr.trim().isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    currentUser.setBirthDate(sdf.parse(birthDateStr));
                } catch (ParseException e) {
                    // 日期格式错误，忽略
                }
            }
            
            // 更新数据库
            if (userDAO.updateUser(currentUser)) {
                session.setAttribute("user", currentUser);
                request.setAttribute("successMessage", "个人信息更新成功");
            } else {
                request.setAttribute("errorMessage", "更新失败，请重试");
            }
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", "系统错误: " + e.getMessage());
        }
        
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}