package com.gcp.gcpdemo.controller;

import com.gcp.gcpdemo.pojo.EmployeePojo;
import com.gcp.gcpdemo.service.inter.EmployeeInterFace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class Employee {
    private final EmployeeInterFace employeeInterFace;
    @GetMapping(value = "/getEmployee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") String id){
        return employeeInterFace.getEmployee(id);
    }
    @GetMapping(value = "/test")
    public ResponseEntity<?> getTest(){
        return ResponseEntity.ok("Welcome to Hello World");
    }
    @PostMapping(value = "/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeePojo employee){

        return employeeInterFace.createEmployee(employee);
    }
    @PutMapping(value = "/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeePojo employee){

        return employeeInterFace.updateEmployee(employee);
    }
    @DeleteMapping(value = "/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id){

        return employeeInterFace.deleteEmployee(id);
    }
}
