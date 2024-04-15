package com.gcp.gcpdemo.service;

import com.gcp.gcpdemo.entity.EmployeeEntity;
import com.gcp.gcpdemo.helper.EmployeeHelper;
import com.gcp.gcpdemo.pojo.EmployeePojo;
import com.gcp.gcpdemo.pojo.ResponsePojo;
import com.gcp.gcpdemo.repository.EmployeeRepository;
import com.gcp.gcpdemo.service.inter.EmployeeInterFace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeeInterFace {
    private final EmployeeRepository employeeRepository;
    @Override
    public ResponseEntity<?> getEmployee(String empId) {
        Optional<EmployeeEntity> empRecord = employeeRepository.findById(UUID.fromString(empId));
        ResponsePojo responsePojo = null;
        if(empRecord.isEmpty()){
            responsePojo=  ResponsePojo.builder().status(HttpStatus.NOT_FOUND).responseMessage("Not Found Any Record").build();
        }else
            responsePojo=  ResponsePojo.builder().status(HttpStatus.OK).responseMessage("Record are available").data(EmployeeHelper.mapToPojo(empRecord.get())).build();
        return ResponseEntity.status(HttpStatus.OK).body(responsePojo);
    }

    @Override
    public ResponseEntity<?> createEmployee(EmployeePojo employee) {
        EmployeeEntity saveRecords = employeeRepository.save(EmployeeHelper.mapToEntity(employee));
        ResponsePojo responsePojo = null;
        if(saveRecords ==null){
            responsePojo=  ResponsePojo.builder().status(HttpStatus.NOT_FOUND).responseMessage("Record Not Created").build();
        }else
            responsePojo=  ResponsePojo.builder().status(HttpStatus.CREATED).responseMessage("Record are created").data(EmployeeHelper.mapToPojo(saveRecords)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePojo);
    }

    @Override
    public ResponseEntity<?> updateEmployee(EmployeePojo employee) {
        EmployeeEntity updateRecords = employeeRepository.saveAndFlush(EmployeeHelper.mapToEntity(employee));
        ResponsePojo responsePojo = null;
        if(updateRecords ==null){
            responsePojo=  ResponsePojo.builder().status(HttpStatus.NOT_FOUND).responseMessage("Record Not Created").build();
        }else
            responsePojo=  ResponsePojo.builder().status(HttpStatus.OK).responseMessage("Record are Updated").data(EmployeeHelper.mapToPojo(updateRecords)).build();
        return ResponseEntity.status(HttpStatus.OK).body(responsePojo);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(String empId) {
        employeeRepository.deleteById(UUID.fromString(empId));
        return ResponseEntity.status(HttpStatus.OK).body( ResponsePojo.builder().status(HttpStatus.OK).responseMessage("Record are deleted").build());
    }
}
