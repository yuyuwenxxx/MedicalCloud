package org.cloud.paas.caserecordservice;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface CaseRecordService_ImplRemote {

    Long saveCaseRecord(Long processId, String nodeName, String diagnosisText, boolean isDraft);

    boolean updateCaseRecord(Long nodeId, Long processId, String diagnosisText, String reminder);

    Map<String, Object> getCaseRecord(Long nodeId, Long processId);

    List<Map<String, Object>> getCaseRecordsByProcess(Long processId);

    List<Map<String, Object>> getCaseRecordsByPatient(Long patientId);

    boolean deleteCaseRecord(Long nodeId, Long processId);

    Long completeAndNext(Long nodeId, Long processId, String nextNodeName);

    boolean setMedicine(Long nodeId, Long processId, Long medicineId);

    boolean setLocation(Long nodeId, Long processId, Long locationId);
}