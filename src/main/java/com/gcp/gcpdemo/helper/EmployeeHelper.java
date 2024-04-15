package com.gcp.gcpdemo.helper;

import com.gcp.gcpdemo.entity.EmployeeEntity;
import com.gcp.gcpdemo.pojo.EmployeePojo;

import java.util.UUID;

public class EmployeeHelper {

    public static EmployeePojo mapToPojo(EmployeeEntity employeeEntity){
        return EmployeePojo.builder().empID(employeeEntity.getEmpId()).empName(employeeEntity.getEmpName()).empNumber(employeeEntity.getEmpCode())
                .empSalary(employeeEntity.getEmpSalary()).build();
    }
    public static EmployeeEntity mapToEntity(EmployeePojo employee){
        return EmployeeEntity.builder().empId(employee.getEmpID()).empName(employee.getEmpName()).empCode(employee.getEmpNumber())
                .empSalary(employee.getEmpSalary()).build();
    }
}
