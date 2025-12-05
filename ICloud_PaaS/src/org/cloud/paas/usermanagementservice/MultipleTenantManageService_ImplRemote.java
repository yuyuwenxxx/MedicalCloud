package org.cloud.paas.usermanagementservice;

import javax.ejb.Remote;
import java.util.*;

@Remote
public interface MultipleTenantManageService_ImplRemote {
    boolean patientRegister(String name, String password, int age);
    boolean doctorRegister(String name, String password, String specialty);
    
}