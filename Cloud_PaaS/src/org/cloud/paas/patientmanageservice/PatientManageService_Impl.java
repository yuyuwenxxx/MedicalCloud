package org.cloud.paas.patientmanageservice;

import java.util.*;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jpa.*;

@Stateless
public class PatientManageService_Impl implements PatientManageService_ImplRemote {

    @EJB
    private PatientFacadeLocal patientFacade;

    @EJB
    private MedicalProcessFacadeLocal medicalProcessFacade;

    @EJB
    private ProcessNodeFacadeLocal processNodeFacade;

    /**
     * 获取所有患者列表
     */
    @Override
    public List<Map<String, Object>> getAllPatients() {
        LogUtil.log("Getting all patients", Level.INFO, null);
        try {
            List<Patient> patients = patientFacade.findAll();
            List<Map<String, Object>> results = new ArrayList<>();
            
            for (Patient patient : patients) {
                results.add(convertPatientToMap(patient));
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to get all patients", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据ID获取患者详情
     */
    @Override
    public Map<String, Object> getPatientById(Long patientId) {
        LogUtil.log("Getting patient by id: " + patientId, Level.INFO, null);
        try {
            Patient patient = patientFacade.findById(patientId);
            if (patient == null) {
                return null;
            }
            return convertPatientToMap(patient);
        } catch (Exception e) {
            LogUtil.log("Failed to get patient by id", Level.SEVERE, e);
            return null;
        }
    }

    /**
     * 搜索患者
     */
    @Override
    public List<Map<String, Object>> searchPatients(String keyword) {
        LogUtil.log("Searching patients with keyword: " + keyword, Level.INFO, null);
        try {
            List<Patient> patients = patientFacade.search(keyword);
            List<Map<String, Object>> results = new ArrayList<>();
            
            for (Patient patient : patients) {
                results.add(convertPatientToMap(patient));
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to search patients", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 分页获取患者列表
     */
    @Override
    public Map<String, Object> getPatientsByPage(int page, int pageSize) {
        LogUtil.log("Getting patients by page: " + page, Level.INFO, null);
        try {
            Map<String, Object> result = new HashMap<>();
            
            // 获取总数
            Long total = patientFacade.getCount();
            
            // 获取分页数据
            List<Patient> patients = patientFacade.findByPage(page, pageSize);
            List<Map<String, Object>> dataList = new ArrayList<>();
            
            for (Patient patient : patients) {
                dataList.add(convertPatientToMap(patient));
            }
            
            result.put("total", total);
            result.put("data", dataList);
            result.put("page", page);
            result.put("pageSize", pageSize);
            result.put("totalPages", (int) Math.ceil((double) total / pageSize));
            
            return result;
        } catch (Exception e) {
            LogUtil.log("Failed to get patients by page", Level.SEVERE, e);
            Map<String, Object> emptyResult = new HashMap<>();
            emptyResult.put("total", 0L);
            emptyResult.put("data", new ArrayList<>());
            return emptyResult;
        }
    }

    /**
     * 获取患者的就诊历史
     */
    @Override
    public List<Map<String, Object>> getPatientMedicalHistory(Long patientId) {
        LogUtil.log("Getting medical history for patient: " + patientId, Level.INFO, null);
        try {
            List<Map<String, Object>> results = new ArrayList<>();
            
            // 获取患者的所有就诊流程
            List<MedicalProcess> processes = medicalProcessFacade.findByPatientId(patientId);
            
            for (MedicalProcess process : processes) {
                Map<String, Object> historyItem = new HashMap<>();
                historyItem.put("processId", process.getProcessId());
                historyItem.put("processStatus", process.getProcessStatus());
                historyItem.put("doctorId", process.getDoctorId());
                historyItem.put("organizationId", process.getOrganizationId());
                historyItem.put("createdAt", process.getCreatedAt());
                historyItem.put("updatedAt", process.getUpdatedAt());
                historyItem.put("completedAt", process.getCompletedAt());
                
                // 获取该流程的所有节点
                List<ProcessNode> nodes = processNodeFacade.findByProcessId(process.getProcessId());
                List<Map<String, Object>> nodeList = new ArrayList<>();
                for (ProcessNode node : nodes) {
                    Map<String, Object> nodeMap = new HashMap<>();
                    nodeMap.put("nodeId", node.getNodeId());
                    nodeMap.put("nodeName", node.getNodeName());
                    nodeMap.put("nodeStatus", node.getNodeStatus());
                    nodeMap.put("diagnosisText", node.getDiagnosisText());
                    nodeMap.put("createAt", node.getCreateAt());
                    nodeList.add(nodeMap);
                }
                historyItem.put("nodes", nodeList);
                
                results.add(historyItem);
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to get medical history", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取医生的待诊患者列表
     */
    @Override
    public List<Map<String, Object>> getRegisteredPatientsByDoctor(Long doctorId) {
        LogUtil.log("Getting registered patients for doctor: " + doctorId, Level.INFO, null);
        try {
            List<Map<String, Object>> results = new ArrayList<>();
            
            // 获取医生的所有"已挂号"状态的流程
            List<MedicalProcess> processes = medicalProcessFacade.findRegisteredByDoctor(doctorId);
            
            for (MedicalProcess process : processes) {
                Map<String, Object> item = new HashMap<>();
                item.put("processId", process.getProcessId());
                item.put("processStatus", process.getProcessStatus());
                item.put("createdAt", process.getCreatedAt());
                item.put("organizationId", process.getOrganizationId());
                
                // 获取患者信息
                Patient patient = patientFacade.findById(process.getPatientId());
                if (patient != null) {
                    item.put("patientId", patient.getPatientId());
                    item.put("patientName", patient.getUsername());
                    item.put("patientPhone", patient.getPhoneNum());
                    item.put("patientGender", patient.getGender());
                }
                
                results.add(item);
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to get registered patients", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取统计数据
     */
    @Override
    public Map<String, Object> getStatistics() {
        LogUtil.log("Getting statistics", Level.INFO, null);
        try {
            Map<String, Object> stats = new HashMap<>();
            
            // 总患者数
            Long totalPatients = patientFacade.getCount();
            stats.put("totalPatients", totalPatients);
            
            // 获取所有流程统计
            List<MedicalProcess> allProcesses = medicalProcessFacade.findAll();
            
            long todayVisits = 0;
            long registeredCount = 0;
            long consultingCount = 0;
            long completedCount = 0;
            
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            Date todayStart = today.getTime();
            
            for (MedicalProcess process : allProcesses) {
                // 今日就诊
                if (process.getCreatedAt() != null && process.getCreatedAt().after(todayStart)) {
                    todayVisits++;
                }
                // 按状态统计
                if (MedicalProcess.STATUS_REGISTERED.equals(process.getProcessStatus())) {
                    registeredCount++;
                } else if (MedicalProcess.STATUS_CONSULTING.equals(process.getProcessStatus())) {
                    consultingCount++;
                } else if (MedicalProcess.STATUS_COMPLETED.equals(process.getProcessStatus())) {
                    completedCount++;
                }
            }
            
            stats.put("todayVisits", todayVisits);
            stats.put("registeredCount", registeredCount);  // 待诊
            stats.put("consultingCount", consultingCount);  // 问诊中
            stats.put("completedCount", completedCount);    // 已完成
            
            return stats;
        } catch (Exception e) {
            LogUtil.log("Failed to get statistics", Level.SEVERE, e);
            Map<String, Object> defaultStats = new HashMap<>();
            defaultStats.put("totalPatients", 0L);
            defaultStats.put("todayVisits", 0L);
            return defaultStats;
        }
    }

    /**
     * 根据流程ID获取患者信息
     */
    @Override
    public Map<String, Object> getPatientByProcess(Long processId) {
        LogUtil.log("Getting patient by process: " + processId, Level.INFO, null);
        try {
            MedicalProcess process = medicalProcessFacade.findById(processId);
            if (process == null) {
                return null;
            }
            
            Patient patient = patientFacade.findById(process.getPatientId());
            if (patient == null) {
                return null;
            }
            
            Map<String, Object> result = convertPatientToMap(patient);
            result.put("processId", process.getProcessId());
            result.put("processStatus", process.getProcessStatus());
            
            return result;
        } catch (Exception e) {
            LogUtil.log("Failed to get patient by process", Level.SEVERE, e);
            return null;
        }
    }

    private Map<String, Object> convertPatientToMap(Patient patient) {
        Map<String, Object> map = new HashMap<>();
        map.put("patientId", patient.getPatientId());
        map.put("username", patient.getUsername());
        map.put("phoneNum", patient.getPhoneNum());
        map.put("gender", patient.getGender());
        return map;
    }
}