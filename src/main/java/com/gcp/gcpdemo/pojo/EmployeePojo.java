package com.gcp.gcpdemo.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class EmployeePojo implements Serializable {
    private UUID empID;
    private String empName;
    private String empNumber;
    private BigDecimal empSalary;
}
