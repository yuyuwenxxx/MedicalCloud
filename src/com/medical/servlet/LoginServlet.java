package com.medical.servlet;

import com.medical.dao.UserDAO;
import com.medical.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 使用注解配置，避免web.xml配置
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("登录请求: username=" + username + ", password=" + password);
        
        // 基本验证
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "用户名和密码不能为空");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // 用户验证
        User user = userDAO.login(username.trim(), password.trim());
        if (user != null) {
            // 登录成功，创建session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userType", user.getUserType());
            
            // 设置会话超时30分钟
            session.setMaxInactiveInterval(30 * 60);
            
            System.out.println("登录成功: " + user.getRealName() + " (" + user.getUserType() + ")");
            
            // 根据用户类型跳转到不同页面
            if ("ADMIN".equals(user.getUserType())) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("profile.jsp");
            }
        } else {
            // 登录失败
            System.out.println("登录失败: 用户名或密码错误");
            request.setAttribute("errorMessage", "用户名或密码错误");
            request.setAttribute("username", username); // 回填用户名
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // 处理退出登录
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    System.out.println("用户退出: " + user.getRealName());
                }
                session.invalidate();
            }
            response.sendRedirect("login.jsp?message=logout_success");
            return;
        }
        
        // 默认显示登录页面
        response.sendRedirect("login.jsp");
    }
}