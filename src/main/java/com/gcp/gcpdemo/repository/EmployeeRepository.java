package com.gcp.gcpdemo.repository;

import com.gcp.gcpdemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,UUID> {
}
