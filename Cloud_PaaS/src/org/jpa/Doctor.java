package org.jpa;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Doctor", catalog = "cloud")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long doctorId;
    private String username;
    private String password;
    private String phoneNum;
    private String gender;
    private String title;           
    private String specialty;      
    private Long organizationId;   

    // Constructors
    public Doctor() {
    }

    public Doctor(Long doctorId) {
        this.doctorId = doctorId;
    }

    // Primary Key
    @Id
    @Column(name = "DoctorID", unique = true, nullable = false)
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_num", nullable = false, length = 20)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Column(name = "gender", nullable = false, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "specialty", nullable = false, length = 255)
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Column(name = "OrganizationID", nullable = false)
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}