package org.cloud.paas.caserecordservice;

import java.util.*;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jpa.*;

@Stateless
public class CaseRecordService_Impl implements CaseRecordService_ImplRemote {

    @EJB
    private ProcessNodeFacadeLocal processNodeFacade;

    @EJB
    private MedicalProcessFacadeLocal medicalProcessFacade;

    @EJB
    private PatientFacadeLocal patientFacade;

    /**
     * 保存病例记录（创建新的流程节点）
     */
    @Override
    public Long saveCaseRecord(Long processId, String nodeName, String diagnosisText, boolean isDraft) {
        LogUtil.log("Saving case record for processId: " + processId, Level.INFO, null);
        try {
            // 验证流程是否存在
            MedicalProcess process = medicalProcessFacade.findById(processId);
            if (process == null) {
                LogUtil.log("Process not found: " + processId, Level.WARNING, null);
                return null;
            }

            // 将之前的节点设置为非当前、非最新
            List<ProcessNode> existingNodes = processNodeFacade.findByProcessId(processId);
            for (ProcessNode node : existingNodes) {
                node.setIsCurrent(false);
                node.setIsLatest(false);
                processNodeFacade.update(node);
            }

            // 创建新节点
            ProcessNode newNode = new ProcessNode();
            
            // 生成新的节点ID
            Long maxNodeId = processNodeFacade.getMaxNodeId(processId);
            Long newNodeId = maxNodeId + 1;
            
            newNode.setNodeId(newNodeId);
            newNode.setProcessId(processId);
            newNode.setNodeName(nodeName);
            newNode.setNodeStatus(isDraft ? "draft" : "active");
            newNode.setDiagnosisText(diagnosisText != null ? diagnosisText : "");
            newNode.setIsCurrent(true);
            newNode.setIsLatest(true);
            newNode.setCreateAt(new Date());
            newNode.setUpdatedAt(new Date());
            
            processNodeFacade.save(newNode);
            
            // 如果不是草稿，更新流程状态为问诊中
            if (!isDraft) {
                process.setProcessStatus(MedicalProcess.STATUS_CONSULTING);
                process.setUpdatedAt(new Date());
                medicalProcessFacade.update(process);
            }
            
            LogUtil.log("Case record saved successfully, nodeId: " + newNodeId, Level.INFO, null);
            return newNodeId;
            
        } catch (Exception e) {
            LogUtil.log("Failed to save case record", Level.SEVERE, e);
            return null;
        }
    }

    /**
     * 更新病例记录
     */
    @Override
    public boolean updateCaseRecord(Long nodeId, Long processId, String diagnosisText, String reminder) {
        LogUtil.log("Updating case record: nodeId=" + nodeId + ", processId=" + processId, Level.INFO, null);
        try {
            ProcessNode node = processNodeFacade.findById(nodeId, processId);
            if (node == null) {
                LogUtil.log("Case record not found", Level.WARNING, null);
                return false;
            }
            
            if (diagnosisText != null) {
                node.setDiagnosisText(diagnosisText);
            }
            if (reminder != null) {
                node.setReminder(reminder);
            }
            node.setUpdatedAt(new Date());
            
            processNodeFacade.update(node);
            LogUtil.log("Case record updated successfully", Level.INFO, null);
            return true;
            
        } catch (Exception e) {
            LogUtil.log("Failed to update case record", Level.SEVERE, e);
            return false;
        }
    }

    /**
     * 获取病例详情
     */
    @Override
    public Map<String, Object> getCaseRecord(Long nodeId, Long processId) {
        LogUtil.log("Getting case record: nodeId=" + nodeId + ", processId=" + processId, Level.INFO, null);
        try {
            ProcessNode node = processNodeFacade.findById(nodeId, processId);
            if (node == null) {
                return null;
            }
            return convertNodeToMap(node);
        } catch (Exception e) {
            LogUtil.log("Failed to get case record", Level.SEVERE, e);
            return null;
        }
    }

    /**
     * 获取流程的所有病例节点
     */
    @Override
    public List<Map<String, Object>> getCaseRecordsByProcess(Long processId) {
        LogUtil.log("Getting case records for processId: " + processId, Level.INFO, null);
        try {
            List<Map<String, Object>> results = new ArrayList<>();
            List<ProcessNode> nodes = processNodeFacade.findByProcessId(processId);
            
            for (ProcessNode node : nodes) {
                results.add(convertNodeToMap(node));
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to get case records by process", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取患者的所有病例记录
     */
    @Override
    public List<Map<String, Object>> getCaseRecordsByPatient(Long patientId) {
        LogUtil.log("Getting case records for patientId: " + patientId, Level.INFO, null);
        try {
            List<Map<String, Object>> results = new ArrayList<>();
            
            // 获取患者的所有流程
            List<MedicalProcess> processes = medicalProcessFacade.findByPatientId(patientId);
            
            // 获取每个流程的病例节点
            for (MedicalProcess process : processes) {
                List<ProcessNode> nodes = processNodeFacade.findByProcessId(process.getProcessId());
                for (ProcessNode node : nodes) {
                    Map<String, Object> map = convertNodeToMap(node);
                    map.put("processStatus", process.getProcessStatus());
                    map.put("doctorId", process.getDoctorId());
                    results.add(map);
                }
            }
            
            return results;
        } catch (Exception e) {
            LogUtil.log("Failed to get case records by patient", Level.SEVERE, e);
            return new ArrayList<>();
        }
    }

    /**
     * 删除病例记录
     */
    @Override
    public boolean deleteCaseRecord(Long nodeId, Long processId) {
        LogUtil.log("Deleting case record: nodeId=" + nodeId + ", processId=" + processId, Level.INFO, null);
        try {
            ProcessNode node = processNodeFacade.findById(nodeId, processId);
            if (node == null) {
                return false;
            }
            processNodeFacade.delete(node);
            LogUtil.log("Case record deleted successfully", Level.INFO, null);
            return true;
        } catch (Exception e) {
            LogUtil.log("Failed to delete case record", Level.SEVERE, e);
            return false;
        }
    }

    /**
     * 完成当前节点，进入下一步
     */
    @Override
    public Long completeAndNext(Long nodeId, Long processId, String nextNodeName) {
        LogUtil.log("Completing node and moving to next: " + nextNodeName, Level.INFO, null);
        try {
            // 获取当前节点
            ProcessNode currentNode = processNodeFacade.findById(nodeId, processId);
            if (currentNode == null) {
                return null;
            }
            
            // 完成当前节点
            currentNode.setNodeStatus("completed");
            currentNode.setIsCurrent(false);
            currentNode.setUpdatedAt(new Date());
            processNodeFacade.update(currentNode);
            
            // 创建下一个节点
            return saveCaseRecord(processId, nextNodeName, "", false);
            
        } catch (Exception e) {
            LogUtil.log("Failed to complete and next", Level.SEVERE, e);
            return null;
        }
    }

    /**
     * 设置节点关联的药品
     */
    @Override
    public boolean setMedicine(Long nodeId, Long processId, Long medicineId) {
        try {
            ProcessNode node = processNodeFacade.findById(nodeId, processId);
            if (node == null) {
                return false;
            }
            node.setMedicineId(medicineId);
            node.setUpdatedAt(new Date());
            processNodeFacade.update(node);
            return true;
        } catch (Exception e) {
            LogUtil.log("Failed to set medicine", Level.SEVERE, e);
            return false;
        }
    }

    /**
     * 设置节点关联的位置
     */
    @Override
    public boolean setLocation(Long nodeId, Long processId, Long locationId) {
        try {
            ProcessNode node = processNodeFacade.findById(nodeId, processId);
            if (node == null) {
                return false;
            }
            node.setLocationId(locationId);
            node.setUpdatedAt(new Date());
            processNodeFacade.update(node);
            return true;
        } catch (Exception e) {
            LogUtil.log("Failed to set location", Level.SEVERE, e);
            return false;
        }
    }

    private Map<String, Object> convertNodeToMap(ProcessNode node) {
        Map<String, Object> map = new HashMap<>();
        map.put("nodeId", node.getNodeId());
        map.put("processId", node.getProcessId());
        map.put("nodeName", node.getNodeName());
        map.put("nodeStatus", node.getNodeStatus());
        map.put("diagnosisText", node.getDiagnosisText());
        map.put("pictures", node.getPictures());
        map.put("reminder", node.getReminder());
        map.put("createAt", node.getCreateAt());
        map.put("updatedAt", node.getUpdatedAt());
        map.put("isCurrent", node.getIsCurrent());
        map.put("isLatest", node.getIsLatest());
        map.put("medicineId", node.getMedicineId());
        map.put("locationId", node.getLocationId());
        return map;
    }
}