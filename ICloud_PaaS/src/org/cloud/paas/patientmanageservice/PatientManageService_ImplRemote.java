package org.cloud.paas.patientmanageservice;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;


@Remote
public interface PatientManageService_ImplRemote {

    List<Map<String, Object>> getAllPatients();

    Map<String, Object> getPatientById(Long patientId);

    List<Map<String, Object>> searchPatients(String keyword);

    Map<String, Object> getPatientsByPage(int page, int pageSize);

    List<Map<String, Object>> getPatientMedicalHistory(Long patientId);

    List<Map<String, Object>> getRegisteredPatientsByDoctor(Long doctorId);

    Map<String, Object> getStatistics();

    Map<String, Object> getPatientByProcess(Long processId);
}