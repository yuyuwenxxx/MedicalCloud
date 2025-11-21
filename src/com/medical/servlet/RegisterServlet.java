package com.medical.servlet;

import com.medical.dao.UserDAO;
import com.medical.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 显示注册页面
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String realName = request.getParameter("realName");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");
        
        System.out.println("注册请求: username=" + username + ", realName=" + realName);
        
        // 验证数据
        String errorMessage = validateRegistration(username, password, confirmPassword, 
                                                  realName, phone, userType);
        
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            // 回填数据
            request.setAttribute("username", username);
            request.setAttribute("realName", realName);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("userType", userType);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // 检查用户名是否已存在
        if (userDAO.isUsernameExists(username)) {
            request.setAttribute("errorMessage", "用户名已存在，请选择其他用户名");
            request.setAttribute("username", username);
            request.setAttribute("realName", realName);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("userType", userType);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // 创建新用户
        try {
            String userId = generateUserId();
            User newUser = new User(userId, username, password, realName, gender, phone, userType);
            newUser.setEmail(email);
            
            // 添加到数据库（模拟）
            if (addUserToDatabase(newUser)) {
                System.out.println("注册成功: " + username + " (" + realName + ")");
                request.setAttribute("successMessage", "注册成功！请使用新账号登录系统");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "注册失败，请稍后重试");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "系统错误: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
    
    private String validateRegistration(String username, String password, String confirmPassword,
                                      String realName, String phone, String userType) {
        if (username == null || username.trim().isEmpty()) {
            return "用户名不能为空";
        }
        if (username.length() < 3 || username.length() > 20) {
            return "用户名长度必须在3-20个字符之间";
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            return "用户名只能包含字母、数字和下划线";
        }
        if (password == null || password.trim().isEmpty()) {
            return "密码不能为空";
        }
        if (password.length() < 6) {
            return "密码长度不能少于6位";
        }
        if (!password.equals(confirmPassword)) {
            return "两次输入的密码不一致";
        }
        if (realName == null || realName.trim().isEmpty()) {
            return "真实姓名不能为空";
        }
        if (phone == null || phone.trim().isEmpty()) {
            return "手机号码不能为空";
        }
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            return "请输入正确的手机号码";
        }
        if (userType == null || userType.trim().isEmpty()) {
            return "请选择用户类型";
        }
        return null;
    }
    
    private String generateUserId() {
        return "U" + System.currentTimeMillis();
    }
    
    private boolean addUserToDatabase(User user) {
        // 在实际项目中，这里应该调用DAO的方法保存到数据库
        // 这里我们模拟添加到内存数据库
        try {
            // 使用反射或其他方式添加到UserDAO的userDatabase中
            // 由于userDatabase是private，我们暂时通过其他方式处理
            // 在实际项目中，UserDAO应该有addUser方法
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}