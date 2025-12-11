package org.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "medical_processes", catalog = "cloud")
public class MedicalProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long processId;
    private Long patientId;
    private Long doctorId;
    private Long organizationId;
    private String processStatus;  
    private Date createdAt;
    private Date updatedAt;
    private Date completedAt;

    // 流程状态常量
    public static final String STATUS_REGISTERED = "registered";    // 已挂号
    public static final String STATUS_CONSULTING = "consulting";    // 问诊中
    public static final String STATUS_COMPLETED = "completed";      // 已完成

    // Constructors
    public MedicalProcess() {
    }

    public MedicalProcess(Long processId) {
        this.processId = processId;
    }

    // Primary Key
    @Id
    @Column(name = "ProcessID", unique = true, nullable = false)
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    @Column(name = "PatientID", nullable = false)
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Column(name = "DoctorID", nullable = false)
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "OrganizationID", nullable = false)
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "process_status", nullable = false, length = 20)
    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_at")
    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}