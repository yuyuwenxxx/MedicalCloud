package org.cloud.paas.diseasemanagementservice;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface DiseaseManagementService_ImplRemote {
	String createProcess(String patientId, String doctorId);
    boolean updateProcessStage(String processId, String stage);
    List<String> getProcessesByPatient(String patientId);
}
