package org.cloud.paas.usermanagementservice;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MultipleTenantManageService_Impl implements MultipleTenantManageService_ImplRemote {

	@Override
	public boolean patientRegister(String name, String password, int age) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doctorRegister(String name, String password, String specialty) {
		// TODO Auto-generated method stub
		return false;
	}
}
