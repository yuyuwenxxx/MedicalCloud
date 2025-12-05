package org.cloud.paas.diseasemanagementservice;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class DiseaseManagementService_Impl implements DiseaseManagementService_ImplRemote {

	@Override
	public String createProcess(String patientId, String doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProcessStage(String processId, String stage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getProcessesByPatient(String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
