package com.medical.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String username;
    private String password;
    private String realName;
    private String gender;
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private String userType; // PATIENT, DOCTOR, ADMIN
    private Date createTime;
    
    public User() {}
    
    public User(String userId, String username, String password, String realName, 
                String gender, String phone, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.gender = gender;
        this.phone = phone;
        this.userType = userType;
        this.createTime = new Date();
    }
    
    // GetterºÍSetter·½·¨
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}