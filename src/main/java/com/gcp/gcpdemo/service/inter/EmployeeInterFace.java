package com.gcp.gcpdemo.service.inter;

import com.gcp.gcpdemo.pojo.EmployeePojo;
import org.springframework.http.ResponseEntity;

public interface EmployeeInterFace {
    ResponseEntity<?> getEmployee(String empId);
    ResponseEntity<?> createEmployee(EmployeePojo employee);
    ResponseEntity<?> updateEmployee(EmployeePojo employee);
    ResponseEntity<?> deleteEmployee(String empId);
}
