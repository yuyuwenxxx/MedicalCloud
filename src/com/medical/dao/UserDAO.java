package com.medical.dao;

import com.medical.model.User;
import java.util.*;

public class UserDAO {
    // 模拟数据库
    private static Map<String, User> userDatabase = new HashMap<>();
    
    static {
        // 初始化测试数据
        initializeTestData();
    }
    
    private static void initializeTestData() {
        User admin = new User("U001", "admin", "123456", "系统管理员", "男", "13800138000", "ADMIN");
        admin.setEmail("admin@medical.com");
        admin.setAddress("北京市");
        
        User patient = new User("U002", "patient01", "123456", "张三", "男", "13900139000", "PATIENT");
        patient.setEmail("zhangsan@example.com");
        patient.setAddress("北京市朝阳区");
        
        User doctor = new User("U003", "doctor01", "123456", "李医生", "女", "13700137000", "DOCTOR");
        doctor.setEmail("doctorli@hospital.com");
        doctor.setAddress("北京市海淀区");
        
        userDatabase.put("admin", admin);
        userDatabase.put("patient01", patient);
        userDatabase.put("doctor01", doctor);
    }
    
    // 用户登录验证
    public User login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    // 检查用户名是否存在
    public boolean isUsernameExists(String username) {
        return userDatabase.containsKey(username);
    }
    
    // 注册新用户
    public boolean register(User user) {
        if (userDatabase.containsKey(user.getUsername())) {
            return false; // 用户名已存在
        }
        userDatabase.put(user.getUsername(), user);
        System.out.println("新用户注册成功: " + user.getUsername() + " (" + user.getRealName() + ")");
        return true;
    }
    
    // 根据用户ID获取用户信息
    public User getUserById(String userId) {
        for (User user : userDatabase.values()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    // 更新用户信息
    public boolean updateUser(User user) {
        if (userDatabase.containsKey(user.getUsername())) {
            userDatabase.put(user.getUsername(), user);
            return true;
        }
        return false;
    }
    
    // 获取所有用户（仅管理员使用）
    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase.values());
    }
    
    // 获取用户数量（用于统计）
    public int getUserCount() {
        return userDatabase.size();
    }
    
    // 根据用户类型获取用户列表
    public List<User> getUsersByType(String userType) {
        List<User> users = new ArrayList<>();
        for (User user : userDatabase.values()) {
            if (userType.equals(user.getUserType())) {
                users.add(user);
            }
        }
        return users;
    }
}