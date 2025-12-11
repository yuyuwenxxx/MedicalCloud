package org.jpa;

import java.util.List;
import javax.ejb.Local;

@Local
public interface MedicalProcessFacadeLocal {

    /**
     * 保存新的就诊流程
     */
    public void save(MedicalProcess entity);

    /**
     * 删除就诊流程
     */
    public void delete(MedicalProcess entity);

    /**
     * 更新就诊流程
     */
    public MedicalProcess update(MedicalProcess entity);

    /**
     * 根据ID查找
     */
    public MedicalProcess findById(Long processId);

    /**
     * 根据患者ID查找所有流程
     */
    public List<MedicalProcess> findByPatientId(Long patientId);

    /**
     * 根据医生ID查找所有流程
     */
    public List<MedicalProcess> findByDoctorId(Long doctorId);

    /**
     * 根据状态查找流程
     */
    public List<MedicalProcess> findByStatus(String status);

    /**
     * 查找医生的待诊患者（已挂号状态 registered）
     * 显示哪些患者已完成挂号等待问诊
     */
    public List<MedicalProcess> findRegisteredByDoctor(Long doctorId);

    /**
     * 查找所有就诊流程
     */
    public List<MedicalProcess> findAll();

    /**
     * 获取最大流程ID（用于生成新ID）
     */
    public Long getMaxProcessId();
}