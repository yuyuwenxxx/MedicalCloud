package org.cloud.paas.organizationmanagementservice;

import javax.ejb.Remote;

@Remote
public interface MultiOrganizationManageService_ImplRemote {
	public boolean HospitalType(int HospitalID, String HospitalName, String HospitalType);
}
