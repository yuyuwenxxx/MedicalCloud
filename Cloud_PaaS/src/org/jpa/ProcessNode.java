package org.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "process_node", catalog = "cloud")
@IdClass(ProcessNodeId.class)
public class ProcessNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long nodeId;
    private Long processId;

    private String nodeName;        
    private String nodeStatus;     
    private String diagnosisText;   
    private String pictures;      
    private String reminder;       
    private Date createAt;         
    private Date updatedAt;        
    private Boolean isCurrent;     
    private Boolean isLatest;      
    private Long medicineId;       
    private Long locationId;       

    // Constructors
    public ProcessNode() {
    }

    public ProcessNode(Long nodeId, Long processId) {
        this.nodeId = nodeId;
        this.processId = processId;
    }

    // Primary Key
    @Id
    @Column(name = "NodeID", nullable = false)
    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    @Id
    @Column(name = "ProcessID", nullable = false)
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    // Other Properties
    @Column(name = "nodeName", nullable = false, length = 255)
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Column(name = "node_status", nullable = false, length = 255)
    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    @Column(name = "diagnosis_text", nullable = false, columnDefinition = "TEXT")
    public String getDiagnosisText() {
        return diagnosisText;
    }

    public void setDiagnosisText(String diagnosisText) {
        this.diagnosisText = diagnosisText;
    }

    @Column(name = "pictures", length = 500)
    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    @Column(name = "reminder", length = 255)
    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "is_current", nullable = false)
    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    @Column(name = "is_latest", nullable = false)
    public Boolean getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(Boolean isLatest) {
        this.isLatest = isLatest;
    }

    @Column(name = "MedicineID")
    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    @Column(name = "LocationID")
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}